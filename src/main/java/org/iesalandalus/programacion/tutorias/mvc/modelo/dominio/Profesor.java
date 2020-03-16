package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
    private static final String ER_NOMBRE = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
    private static final String ER_DNI = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";
    private static final String ER_CORREO = ".+@[a-zA-Z]+\\.[a-zA-Z]+";
    
    private String nombre;
    private String dni;
    private String correo;
    
    public Profesor (String nombre, String dni, String correo) {
        this.setNombre(nombre);
        this.setDni(dni);
        this.setCorreo(correo);
    }
    
    public Profesor (Profesor profesor) {
        if (profesor == null) {
            throw new NullPointerException("ERROR: No es posible copiar un profesor nulo.");
        }
        this.setNombre(profesor.getNombre());
        this.setDni(profesor.getDni());
        this.setCorreo(profesor.getCorreo());
    }
    
    public String getNombre () {
        return this.nombre;
    }
    
    private void setNombre (String nombre) {
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
    
    public String getDni () {
        return this.dni;
    }
    
    private void setDni (String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
        }
        
        if (dni.trim().equals("")) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
        }
        
        if (!this.comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
        }
        
        this.dni = dni;
    }
    
    private boolean comprobarLetraDni (String dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        Pattern pattern = Pattern.compile(this.ER_DNI);
        Matcher matcher = pattern.matcher(dni);
        
        if (matcher.matches()) {
            String letra = matcher.group(2);
            Integer numeros = Integer.parseInt(matcher.group(1));
            
            Integer posicion = numeros % 23;
            String letraCorrecta = letras.substring(posicion, posicion + 1);
            
            if (letraCorrecta.equals(letra)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public String getCorreo () {
        return this.correo;
    }
    
    private void setCorreo (String correo) {
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
    
    public static Profesor getProfesorFicticio (String dni) {
        return new Profesor ("Profesor Ficticio", dni, "profesor@ficticio.com");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.dni);
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nombre=" + this.getNombre() + " (" + this.getIniciales() + "), DNI=" + this.getDni() + ", correo=" + this.getCorreo();
    }
}
