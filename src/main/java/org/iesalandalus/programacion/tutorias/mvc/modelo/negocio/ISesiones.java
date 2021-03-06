package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface ISesiones {
    void comenzar();

    void terminar();

    List<Sesion> get();

    List<Sesion> get (Tutoria tutoria);

    int getTamano();

    void insertar(Sesion sesion) throws OperationNotSupportedException;

    Sesion buscar(Sesion sesion);

    void borrar(Sesion sesion) throws OperationNotSupportedException;
}
