package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {    
    private static final String ER_NOMBRE = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
    private static final String PREFIJO_EXPEDIENTE  = "SP_";
    private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
    
    private static int ultimoidentificador = 0;
    
    private String nombre;
    private String correo;
    private String expediente;
    
    public Alumno (String nombre, String correo) {
        Alumno.incrementaUltimoIdentificador();

        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setExpediente();
    }
    
    public Alumno (Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }       
        
        this.setCorreo(alumno.getCorreo());
        this.setNombre(alumno.getNombre());
        this.setExpediente();
    }
    
    public static Alumno getAlumnoFicticio (String correo) {
        return new Alumno ("Alumno Ficticio", correo);
    }

    public String getNombre() {
        return this.nombre;
    }

    private void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
        }
        
        if (nombre.trim().equals("")) {
            throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
        }
        
        nombre = this.formateaNombre(nombre);
        
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
        }
        
        this.nombre = nombre;
    }

    private String formateaNombre (String nombre) {
        nombre = nombre.trim();
        nombre = nombre.replaceAll("\\s+", " ");
        nombre = nombre.toLowerCase();

        String[] palabras = nombre.split(" ");
        String nombreCapitalizado = "";

        for (String palabra : palabras){
            if (!palabra.isEmpty()) {
                nombreCapitalizado = nombreCapitalizado + " " + palabra.substring(0, 1).toUpperCase() + palabra.substring(1);
            }
        }

        return nombreCapitalizado.trim();
    }
    
    private String getIniciales () {
        Pattern p = Pattern.compile("((^| )[A-Za-z])");
        Matcher m = p.matcher(this.nombre);
        
        String iniciales = "";
        
        while (m.find()) {
            iniciales += m.group().trim();
        }
        
        return iniciales.toUpperCase();
    }
    
    public String getCorreo() {
        return this.correo;
    }

    private void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo no puede ser nulo.");
        }
        
        if (correo.trim().equals("")) {
            throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
        }
        
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
        }
        
        this.correo = correo;
    }

    public String getExpediente() {
        return this.expediente;
    }

    private void setExpediente() {
        this.expediente = PREFIJO_EXPEDIENTE + this.getIniciales() + "_" + Alumno.ultimoidentificador;
    }
    
    private static void incrementaUltimoIdentificador () {
        Alumno.ultimoidentificador += 1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nombre=" + this.getNombre() + " (" + this.getIniciales() + "), correo=" + this.getCorreo() + ", expediente=" + this.getExpediente();
    }
}
