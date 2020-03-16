package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IProfesores {
    void comenzar();

    void terminar();

    List<Profesor> get();

    int getTamano();

    void insertar(Profesor profesor) throws OperationNotSupportedException;

    Profesor buscar(Profesor profesor);

    void borrar(Profesor profesor) throws OperationNotSupportedException;
}
