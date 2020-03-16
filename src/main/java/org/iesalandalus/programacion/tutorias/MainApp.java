package org.iesalandalus.programacion.tutorias;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class MainApp {
    public static void main(String[] args) throws OperationNotSupportedException {
        IModelo IModelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
        IVista IVista = FactoriaVista.TEXTO.crear();
        IControlador IControlador = new Controlador(IModelo, IVista);
        IControlador.comenzar();
    }
}
