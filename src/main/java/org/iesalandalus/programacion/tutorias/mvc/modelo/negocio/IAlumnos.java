package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IAlumnos {
    List<Alumno> get();

    int getTamano();

    void insertar(Alumno alumno) throws OperationNotSupportedException;

    Alumno buscar(Alumno alumno);

    void borrar(Alumno alumno) throws OperationNotSupportedException;
}
