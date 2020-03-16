package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.util.List;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.utilidades.Entrada;

public class VistaTexto implements org.iesalandalus.programacion.tutorias.mvc.vista.IVista {
    private IControlador IControlador;
    
    public VistaTexto() {
        Opcion.setVistaTexto(this);
    }
    
    @Override
    public void setControlador(IControlador IControlador) {
        if (IControlador == null) {
            throw new NullPointerException("Controlador no puede ser nulo");
        }
        
        this.IControlador = IControlador;
    }
    
    @Override
    public void comenzar() {
        int opcionSeleccionada;
        Opcion opcion = null;
        
        do {
            Consola.mostrarMenu();
            
            opcionSeleccionada = Entrada.entero();
            
            if (Opcion.esOrdinalValido(opcionSeleccionada)) {
                opcion = Opcion.getOpcionSegunOrdinal(opcionSeleccionada);
                Consola.mostrarCabecera(opcion.toString());
                opcion.ejecutar();
                System.out.println("_____________________________________");
                System.out.println("Seleccione una opción:");
            }
        } while (opcion.ordinal() != Opcion.SALIR.ordinal());
    }
    
    @Override
    public void terminar() {
        this.IControlador.terminar();
    }
    
    public void insertarAlumno () {
        try {
            Alumno alumno = Consola.leerAlumno();
            this.IControlador.insertar(alumno);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarAlumno () {
        try {
            Alumno alumno = Consola.leerAlumnoFicticio();
            Alumno alumnoEncontrado = this.IControlador.buscar(alumno);
            System.out.println("Alumno encontrado: " + alumnoEncontrado);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borrarAlumno () {
        try {
            Alumno alumno = Consola.leerAlumnoFicticio();
            this.IControlador.borrar(alumno);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarAlumnos () {
        List<Alumno> alumnos = this.IControlador.getAlumnos();
        
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos que mostrar.");
        }
        else {
            for (Alumno alumno : alumnos) {
                if (alumno != null) {
                    System.out.println(alumno);               
                }
            }
        }
    }
    
    public void insertarProfesor () {
        try {
            Profesor profesor = Consola.leerProfesor();
            this.IControlador.insertar(profesor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarProfesor () {
        try {
            Profesor profesor = Consola.leerProfesorFicticio();
            Profesor profesorEncontrado = this.IControlador.buscar(profesor);
            System.out.println("Profesor encontrado: " + profesorEncontrado);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borrarProfesor () {
        try {
            Profesor profesor = Consola.leerProfesorFicticio();
            this.IControlador.borrar(profesor);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarProfesores () {
        List<Profesor> profesores = this.IControlador.getProfesores();
        
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores que mostrar.");
        }
        
        else {
            for (Profesor profesor : profesores) {
                if (profesor != null) {
                    System.out.println(profesor);
                }
            }
        }
    }
    
    public void insertarTutoria () {
        try {
            Tutoria tutoria = Consola.leerTutoria();
            this.IControlador.insertar(tutoria);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarTutoria () {
        try {
            Tutoria tutoria = Consola.leerTutoria();
            Tutoria tutoriaEncontrada = this.IControlador.buscar(tutoria);
            System.out.println("Tutoria encontrada: " + tutoriaEncontrada);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borrarTutoria () {
        try {
            Tutoria tutoria = Consola.leerTutoria();
            this.IControlador.borrar(tutoria);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarTutorias () {
        List<Tutoria> tutorias = this.IControlador.getTutorias();
        
        if (tutorias.isEmpty()) {
            System.out.println("No hay tutorias que mostrar");
        }
        else {
            for (Tutoria tutoria : tutorias) {
                if (tutoria != null) {
                    System.out.println(tutoria);                
                }
            }
        }
    }
    
    public void listarTutoriasProfesor () {
        try {
            Profesor profesor = Consola.leerProfesorFicticio();
            List<Tutoria> tutorias = this.IControlador.getTutorias(profesor);
        
            if (tutorias.isEmpty()) {
                System.out.println("No hay tutorias que mostrar");
            }
            else {
                for (Tutoria tutoria : tutorias) {
                    if (tutoria != null) {
                        System.out.println(tutoria);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertarSesion () {
        try {
            Sesion sesion = Consola.leerSesion();
            this.IControlador.insertar(sesion);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarSesion () {
        try {
            Sesion sesion = Consola.leerSesionFicticia();
            Sesion sesionEncontrada = this.IControlador.buscar(sesion);
            System.out.println(sesionEncontrada);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borrarSesion () {
        try {
            Sesion sesion = Consola.leerSesionFicticia();
            this.IControlador.borrar(sesion);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarSesiones () {
        List<Sesion> sesiones = this.IControlador.getSesiones();
        
        if (sesiones.isEmpty()) {
            System.out.println("No hay sesiones que mostrar.");
        }
        else {
            for (Sesion sesion : sesiones) {
                if (sesion != null) {
                    System.out.println(sesion);
                }
            }
        }
    }
    
    public void listarSesionesTutoria () {
        try {
            Tutoria tutoria = Consola.leerTutoria();
            List<Sesion> sesiones = this.IControlador.getSesiones(tutoria);

            if (sesiones.isEmpty()) {
                System.out.println("No hay sesiones que mostrar.");
            }
            else {
                for (Sesion sesion : sesiones) {
                    if (sesion != null) {
                        System.out.println(sesion);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertarCita () {
        try {
            Cita cita = Consola.leerCita();
            this.IControlador.insertar(cita);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void buscarCita () {
        try {
            Cita cita = Consola.leerCita();
            Cita citaEncontrada = this.IControlador.buscar(cita);
            System.out.println(citaEncontrada);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void borrarCita () {
        try {
            Cita cita = Consola.leerCita();
            Cita citaEncontrada = this.IControlador.buscar(cita);
            System.out.println(citaEncontrada);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarCitas () {
        List<Cita> citas = this.IControlador.getCitas();
        
        if (citas.isEmpty()) {
            System.out.println("No hay citas que mostrar.");
        }
        else {
            for (Cita cita : citas) {
                if (cita != null) {
                    System.out.println(cita);
                }
            }
        }
    }
    
    public void listarCitasSesion () {
        try {
            Sesion sesion = Consola.leerSesion();
            List<Cita> citas = this.IControlador.getCitas(sesion);

            if (citas.isEmpty()) {
                System.out.println("No hay citas que mostrar.");
            }
            else {
                for (Cita cita : citas) {
                    if (cita != null) {
                        System.out.println(cita);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void listarCitasAlumno () {
        try {
            Alumno alumno = Consola.leerAlumnoFicticio();
            List<Cita> citas = this.IControlador.getCitas(alumno);

            if (citas.isEmpty()) {
                System.out.println("No hay citas que mostrar.");
            }
            else {
                for (Cita cita : citas) {
                    if (cita != null) {
                        System.out.println(cita);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

