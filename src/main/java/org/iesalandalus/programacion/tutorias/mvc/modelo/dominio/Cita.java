package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

public class Cita implements Serializable {
    private Sesion sesion;
    private Alumno alumno;
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalTime hora;
    
    public Cita (Alumno alumno, Sesion sesion, LocalTime hora) {
        this.setAlumno(alumno);
        this.setSesion(sesion);
        this.setHora(hora);
    }
    
    public Cita (Cita cita) {
        if (cita == null) {
            throw new NullPointerException("ERROR: No es posible copiar una cita nula.");
        }
        
        this.setAlumno(cita.getAlumno());
        this.setSesion(cita.getSesion());
        this.setHora(cita.getHora());
    }
    
    public Alumno getAlumno () {
        return new Alumno(this.alumno);
    }
    
    private void setAlumno (Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        
        this.alumno = new Alumno (alumno);
    }
    
    public Sesion getSesion () {
        return new Sesion (this.sesion);
    }
    
    private void setSesion (Sesion sesion) {
        if (sesion == null) {
            throw new NullPointerException("ERROR: La sesión no puede ser nula.");
        }
        
        this.sesion = new Sesion (sesion);
    }
    
    public LocalTime getHora () {
        return this.hora;
    }
    
    private void setHora (LocalTime hora) {
        if (hora == null) {
            throw new NullPointerException("ERROR: La hora no puede ser nula.");
        }

        if (
            hora.isBefore(sesion.getHoraInicio()) || 
            hora.isAfter(sesion.getHoraFin().minusMinutes(sesion.getMinutosDuracion()))
        ) {
            throw new IllegalArgumentException("ERROR: La hora debe estar comprendida entre la hora de inicio y fin de la sesión.");
        }
        
        long diffTiempo = (hora.toSecondOfDay() - sesion.getHoraInicio().toSecondOfDay()) / 60;

        if (diffTiempo % sesion.getMinutosDuracion() != 0) {
            throw new IllegalArgumentException("ERROR: La hora debe comenzar en un múltiplo de los minutos de duración.");
        }

        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.sesion);
        hash = 31 * hash + Objects.hashCode(this.alumno);
        hash = 31 * hash + Objects.hashCode(this.hora);
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
        final Cita other = (Cita) obj;
        if (!Objects.equals(this.sesion, other.sesion)) {
            return false;
        }
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "alumno=" + alumno + ", sesion=" + sesion + ", hora=" + hora.format(FORMATO_HORA);
    }
}
