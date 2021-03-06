package org.iesalandalus.programacion.tutorias.mvc.controlador;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IControlador {
    void comenzar();

    void terminar();

    void insertar (Alumno alumno) throws OperationNotSupportedException;

    void insertar (Profesor profesor) throws OperationNotSupportedException;

    void insertar (Tutoria tutoria) throws OperationNotSupportedException;

    void insertar (Sesion sesion) throws OperationNotSupportedException;

    void insertar (Cita cita) throws OperationNotSupportedException;

    Alumno buscar(Alumno alumno);

    Profesor buscar(Profesor profesor);

    Tutoria buscar(Tutoria tutoria);

    Sesion buscar(Sesion sesion);

    Cita buscar(Cita cita);

    void borrar(Alumno alumno) throws OperationNotSupportedException;

    void borrar(Profesor profesor) throws OperationNotSupportedException;

    void borrar(Tutoria tutoria) throws OperationNotSupportedException;

    void borrar(Sesion sesion) throws OperationNotSupportedException;

    void borrar(Cita cita) throws OperationNotSupportedException;

    List<Alumno> getAlumnos();

    List<Profesor> getProfesores();

    List<Tutoria> getTutorias();

    List<Tutoria> getTutorias(Profesor profesor);

    List<Sesion> getSesiones();

    List<Sesion> getSesiones(Tutoria tutoria);

    List<Cita> getCitas();

    List<Cita> getCitas(Sesion sesion);

    List<Cita> getCitas(Alumno alumno);
}
