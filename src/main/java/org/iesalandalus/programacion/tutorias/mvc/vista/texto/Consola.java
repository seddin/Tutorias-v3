package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
    private Consola () {}
    
    public static void mostrarMenu () {
        System.out.println("[ Menu ]");
        
        for (Opcion opcion: Opcion.values()) {
            System.out.println(opcion);
        }
        
        System.out.println("Seleccione una opción:");
    }
    
    public static void mostrarCabecera (String cabecera) {
        String barra = "";
        
        if (cabecera.length() == 0 || cabecera == null) {
            throw new IllegalArgumentException("Error cabecera introducida!");
        }
        
        System.out.println(cabecera);
        
        for (int i = 0; i <= cabecera.length(); i++) {
            barra = barra + "-";
        }
        
        System.out.println(barra);
        
    }
    
    public static int elegirOpcion () {
        int opcion;
        
        do {
            System.out.println("Seleccione una opcion:");
            
            opcion = Entrada.entero();
        } while (opcion <= 0);
        
        return opcion;
    }
    
    public static Alumno leerAlumno () {
        String nombre, correo;
        
        do {
            System.out.println("Introduzca el nombre del alumno: ");
            nombre = Entrada.cadena();
        } while (nombre.length() < 0);
        
        do {
            System.out.println("Y ahora introduzca el correo del alumno: ");
            correo = Entrada.cadena();
        } while (correo.length() < 0);
        
        return new Alumno(nombre, correo);
    }
    
    public static Alumno leerAlumnoFicticio () {
        String correo;
        
        do {
            System.out.println("Introduzca el correo del alumno: ");
            correo = Entrada.cadena();
        } while (correo.length() < 0);
        
        return Alumno.getAlumnoFicticio(correo);
    }
    
    public static Profesor leerProfesor () {
        String nombre, dni, correo;
        
        do {
            System.out.println("Introduzca el nombre del profesor: ");
            nombre = Entrada.cadena();
        } while (nombre.length() < 0);

        do {
            System.out.println("Introduzca el DNI del profesor: ");
            dni = Entrada.cadena();
        } while (dni.length() < 0);
        
        do {
            System.out.println("Introduzca el correo del profesor: ");
            correo = Entrada.cadena();
        } while (correo.length() < 0);
    
        
        return new Profesor(nombre, dni, correo);
    }
    
    public static Profesor leerProfesorFicticio () {
        String dni;
        
        do {
            System.out.println("Introduzca el DNI del profesor: ");
            dni = Entrada.cadena();
        } while (dni.length() < 0);
        
        return Profesor.getProfesorFicticio(dni);
    }
    
    public static Tutoria leerTutoria () {
        Profesor profesor;
        String nombreTutoria;
        
        profesor = Consola.leerProfesorFicticio();
        
        do {
            System.out.println("Introduzca el nombre de la tutoria:");
            nombreTutoria = Entrada.cadena();
        } while (nombreTutoria.length() <= 0);
        
        
        return new Tutoria(profesor, nombreTutoria);
    }
    
    public static Sesion leerSesion () {
        Tutoria tutoria;
        
        LocalDate fecha = null; 
        String fechaCadena;
        
        LocalTime horaInicio = null;
        String horaInicioCadena;
        
        LocalTime horaFin = null;
        String horaFinCadena;
        
        int minutosDuracion;

        tutoria = Consola.leerTutoria();
        
        // Fecha
        do {
            System.out.println("Introduzca una fecha con el siguiente formato (2020-02-16):");
            fechaCadena = Entrada.cadena();

            try {
                fecha = LocalDate.parse(fechaCadena);
            } 
            catch (DateTimeException e) {
                fecha = null;
            }
        } while (fecha == null);
        
        // Hora Inicio
        do {
            System.out.println("Introduzca la hora de inicio con el siguiente formato (17:46):");
            horaInicioCadena = Entrada.cadena();

            try {
                horaInicio = LocalTime.parse(horaInicioCadena);
            } 
            catch (DateTimeException e) {
                horaInicio = null;
            }
        } while (horaInicio == null);
        
        // Hota Fin
        do {
            System.out.println("Introduzca la hora de fin con el siguiente formato (19:53):");
            horaFinCadena = Entrada.cadena();

            try {
                horaFin = LocalTime.parse(horaFinCadena);
            } 
            catch (DateTimeException e) {
                horaFin = null;
            }
        } while (horaFin == null);
        
        // Duracion Minutos
        do {
            System.out.println("Cuanto durarar la sesion: ");
            minutosDuracion = Entrada.entero();
        } while (minutosDuracion <= 0);
        
        
        return new Sesion (tutoria, fecha, horaInicio, horaFin, minutosDuracion);
    }
    
    public static Sesion leerSesionFicticia () {
        Tutoria tutoria;
        
        LocalDate fecha = null; 
        String fechaCadena;
        
        tutoria = Consola.leerTutoria();
        
        // Fecha
        do {
            System.out.println("Introduzca una fecha con el siguiente formato (2020-02-16):");
            fechaCadena = Entrada.cadena();

            try {
                fecha = LocalDate.parse(fechaCadena);
            } 
            catch (DateTimeException e) {
                fecha = null;
            }
        } while (fecha == null);
        
        
        
        return Sesion.getSesionFicticia(tutoria, fecha);
    }
    
    public static Cita leerCita () {
        Alumno alumno;
        Sesion sesion;
        LocalTime hora  = null;
        String horaCadena;
        
        alumno = Consola.leerAlumno();
        sesion = Consola.leerSesion();
        
        // Hora
        do {
            System.out.println("Introduzca la hora con el siguiente formato (17:46):");
            horaCadena = Entrada.cadena();

            try {
                hora = LocalTime.parse(horaCadena);
            } 
            catch (DateTimeException e) {
                hora = null;
            }
        } while (hora == null);
        
        return new Cita(alumno, sesion, hora);
    }
}
