package org.iesalandalus.programacion.tutorias.mvc.controlador;

import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class Controlador implements IControlador {
    private static IModelo IModelo;
    private static IVista IVista;
    
    public Controlador (IModelo IModelo, IVista IVista) {
        this.IModelo = IModelo;
        this.IVista = IVista;
        this.IVista.setControlador(this);
    }
    
    @Override
    public void comenzar() {
        this.IModelo.comenzar();
        this.IVista.comenzar();
    }
    
    @Override
    public void terminar() {
        this.IModelo.terminar();
        System.out.println("Saliendo...");
    }
    
    // Insertar
    
    @Override
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        IModelo.insertar(alumno);
    }
    
    @Override
    public void insertar(Profesor profesor) throws OperationNotSupportedException {
        IModelo.insertar(profesor);
    }
    
    @Override
    public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
        IModelo.insertar(tutoria);
    }
    
    @Override
    public void insertar(Sesion sesion) throws OperationNotSupportedException {
        IModelo.insertar(sesion);
    }
    
    @Override
    public void insertar(Cita cita) throws OperationNotSupportedException {
        IModelo.insertar(cita);
    }
    
    // Buscar
    
    @Override
    public Alumno buscar(Alumno alumno) {
        return IModelo.buscar(alumno);
    }
    
    @Override
    public Profesor buscar(Profesor profesor) {
        return IModelo.buscar(profesor);
    }
    
    @Override
    public Tutoria buscar(Tutoria tutoria) {
        return IModelo.buscar(tutoria);
    }
    
    @Override
    public Sesion buscar(Sesion sesion) {
        return IModelo.buscar(sesion);
    }
    
    @Override
    public Cita buscar(Cita cita) {
        return IModelo.buscar(cita);
    }
    
    // Borrar
    
    @Override
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        IModelo.borrar(alumno);
    }
    
    @Override
    public void borrar(Profesor profesor) throws OperationNotSupportedException {
        IModelo.borrar(profesor);
    }
    
    @Override
    public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
        IModelo.borrar(tutoria);
    }
    
    @Override
    public void borrar(Sesion sesion) throws OperationNotSupportedException {
        IModelo.borrar(sesion);
    }
    
    @Override
    public void borrar(Cita cita) throws OperationNotSupportedException {
        IModelo.borrar(cita);
    }
    
    // Getters
    
    @Override
    public List<Alumno> getAlumnos() {
        return IModelo.getAlumnos();
    }
    
    @Override
    public List<Profesor> getProfesores() {
        return IModelo.getProfesores();
    }
    
    @Override
    public List<Tutoria> getTutorias() {
        return IModelo.getTutorias();
    }
    
    @Override
    public List<Tutoria> getTutorias(Profesor profesor) {
        return IModelo.getTutorias(profesor);
    }
    
    @Override
    public List<Sesion> getSesiones() {
        return IModelo.getSesiones();
    }
    
    @Override
    public List<Sesion> getSesiones(Tutoria tutoria) {
        return IModelo.getSesiones(tutoria);
    }
    
    @Override
    public List<Cita> getCitas() {
        return IModelo.getCitas();
    }
    
    @Override
    public List<Cita> getCitas(Sesion sesion) {
        return IModelo.getCitas(sesion);
    }
    
    @Override
    public List<Cita> getCitas(Alumno alumno) {
        return IModelo.getCitas(alumno);
    }
}
