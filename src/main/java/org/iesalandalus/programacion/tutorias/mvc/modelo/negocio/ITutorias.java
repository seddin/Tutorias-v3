package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface ITutorias {
    List<Tutoria> get();

    List<Tutoria> get (Profesor profesor);

    int getTamano();

    void insertar(Tutoria tutoria) throws OperationNotSupportedException;

    Tutoria buscar(Tutoria tutoria);

    void borrar(Tutoria tutoria) throws OperationNotSupportedException;
}
