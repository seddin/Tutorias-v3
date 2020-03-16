package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.*;

public class FactoriaFuenteDatosFicheros implements IFuenteDatos {
    public FactoriaFuenteDatosFicheros() {

    }

    @Override
    public IAlumnos crearAlumnos() {
        return new Alumnos();
    }

    @Override
    public IProfesores crearProfesores() {
        return new Profesores();
    }

    @Override
    public ITutorias crearTutorias() {
        return new Tutorias();
    }

    @Override
    public ISesiones crearSesiones() {
        return new Sesiones();
    }

    @Override
    public ICitas crearCitas() {
        return new Citas();
    }
}
