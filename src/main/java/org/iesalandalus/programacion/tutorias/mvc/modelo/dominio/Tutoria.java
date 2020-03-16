package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Tutoria implements Serializable {
    private Profesor profesor;
    private String nombre;
    
    public Tutoria (Profesor profesor, String nombre) {
        this.setProfesor(profesor);
        this.setNombre(nombre);
    }
    
    public Tutoria (Tutoria tutoria) {
        if (tutoria == null) {
            throw new NullPointerException("ERROR: No es posible copiar una tutoría nula.");
        }
        this.setProfesor(tutoria.getProfesor());
        this.setNombre(tutoria.getNombre());
    }
    
    public Profesor getProfesor () {
        return new Profesor(this.profesor);
    }
    
    private void setProfesor (Profesor profesor) {
        if (profesor == null) {
            throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
        }
        
        this.profesor = new Profesor(profesor);
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
        
        this.nombre = nombre.trim();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.profesor);
        hash = 67 * hash + Objects.hashCode(this.nombre);
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
        final Tutoria other = (Tutoria) obj;
        if (!Objects.equals(this.nombre.toLowerCase(), other.nombre.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "profesor=" + profesor + ", nombre=" + nombre;
    }    
}
