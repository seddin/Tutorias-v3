package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;

public interface IVista {
    void setControlador (IControlador IControlador);

    void comenzar();

    void terminar();
}
