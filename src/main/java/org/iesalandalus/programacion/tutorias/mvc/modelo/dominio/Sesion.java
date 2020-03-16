package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Sesion {
    
    private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.of(16, 0);
    private static final LocalTime HORA_FIN_CLASES = LocalTime.of(22, 15);
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int minutosDuracion;
    
    private Tutoria tutoria;
    
    public Sesion (
        Tutoria tutoria, 
        LocalDate fecha, 
        LocalTime horaInicio, 
        LocalTime horaFin, 
        int minutosDuracion
    ) {
        this.setTutoria(tutoria);
        this.setFecha(fecha);
        this.setHoraInicio(horaInicio);
        this.setHoraFin(horaFin);
        this.setMinutosDuracion(minutosDuracion);
        
        this.comprobarValidezSesion();
    }
    
    public Sesion (Sesion sesion) {
        if (sesion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
        }
        
        this.setTutoria(sesion.getTutoria());
        this.setFecha(sesion.getFecha());
        this.setHoraInicio(sesion.getHoraInicio());
        this.setHoraFin(sesion.getHoraFin());
        this.setMinutosDuracion(sesion.getMinutosDuracion());
        
        this.comprobarValidezSesion();
    }
    
    public static Sesion getSesionFicticia (Tutoria tutoria, LocalDate fecha) {
        LocalTime horaInicioFicticia = LocalTime.of(16, 00);
        LocalTime horaFinFicticia = LocalTime.of(17, 30);
        
        return new Sesion(tutoria, fecha, HORA_COMIENZO_CLASES, HORA_FIN_CLASES, 1);
    }
    
    
    public Tutoria getTutoria () {
        return new Tutoria(this.tutoria);
    }
    
    private void setTutoria (Tutoria tutoria) {
        if (tutoria == null) {
            throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
        }
        
        this.tutoria = new Tutoria(tutoria);
    }
    
    public LocalDate getFecha () {
        return this.fecha;
    }
    
    private void setFecha (LocalDate fecha) {
        if (fecha == null) {
            throw new NullPointerException("ERROR: La fecha no puede ser nula.");
        }
        
        this.fecha = fecha;
    }
    
    public LocalTime getHoraInicio () {
        return this.horaInicio;
    }
    
    private void setHoraInicio (LocalTime horaInicio) {
        if (horaInicio == null) {
            throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
        }
        
        this.horaInicio = horaInicio;
    }
    
    public LocalTime getHoraFin () {
        return this.horaFin;
    }
    
    private void setHoraFin (LocalTime horaFin) {
        if (horaFin == null) {
            throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
        }
        
        this.horaFin = horaFin;
    }
    
    public int getMinutosDuracion () {
        return this.minutosDuracion;
    }
    
    private void setMinutosDuracion (int minutosDuracion) {
        if (minutosDuracion == 0) {
            throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
        }
        
        this.minutosDuracion = minutosDuracion;
    }
    
    private void comprobarValidezSesion () {
        // Todo
        // Debe ser anterior a fecha actual
        // Entre hora inicio y fin de clases
        // Posterior al dia actual
        
        // fecha
        if (this.fecha.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha no puede ser menor que hoy");
        }
        
        if (this.fecha.equals(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras.");
        }
        
        // hora inicio
        if (this.horaInicio.equals(HORA_FIN_CLASES)) {
            // Cumple el test, pero no esta bien
            throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
        }
        
        if (this.horaInicio.isBefore(HORA_COMIENZO_CLASES)) {
            throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
        }
        
        if (this.horaInicio.isAfter(HORA_FIN_CLASES)) {
            throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
        }
        
        // hora fin
        if (this.horaFin.isBefore(HORA_COMIENZO_CLASES)) {
            throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
        }
        
        if (this.horaFin.isAfter(HORA_FIN_CLASES)) {
            throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
        }
        
        if (this.horaInicio.until(this.horaFin, ChronoUnit.MINUTES) == 0) {
            throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
        }
        
        if (this.horaInicio.isAfter(this.horaFin)) {
            throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
        }
        
        long diffMinutos = this.horaInicio.until(this.horaFin, ChronoUnit.MINUTES);
        
        if (diffMinutos % this.minutosDuracion != 0) {
            throw new IllegalArgumentException("ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
        }
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.fecha);
        hash = 53 * hash + Objects.hashCode(this.tutoria);
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
        final Sesion other = (Sesion) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.tutoria, other.tutoria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tutoria=" + tutoria + ", fecha=" + fecha.format(FORMATO_FECHA) + ", horaInicio=" + horaInicio.format(FORMATO_HORA) + ", horaFin=" + horaFin.format(FORMATO_HORA) + ", minutosDuracion=" + minutosDuracion;
    }
    
}
