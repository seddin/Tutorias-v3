package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.*;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria.*;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class Modelo implements IModelo {
    private IProfesores IProfesores;
    private ITutorias ITutorias;
    private ISesiones ISesiones;
    private ICitas ICitas;
    private IAlumnos IAlumnos;
    
    public Modelo (IFuenteDatos fuenteDatos) {
        this.IProfesores = fuenteDatos.crearProfesores();
        this.ITutorias = fuenteDatos.crearTutorias();
        this.ISesiones = fuenteDatos.crearSesiones();
        this.ICitas = fuenteDatos.crearCitas();
        this.IAlumnos = fuenteDatos.crearAlumnos();
    }
    
    // Insertar
    @Override
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        this.IAlumnos.insertar(alumno);
    }
    
    @Override
    public void insertar(Profesor profesor) throws OperationNotSupportedException {
        this.IProfesores.insertar(profesor);
    }
    
    @Override
    public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
        if (tutoria == null) {
            throw new NullPointerException ("ERROR: No se puede insertar una tutoría nula.");
        }
        
        Profesor profesor = tutoria.getProfesor();
        
        if (this.IProfesores.buscar(profesor) == null) {
            throw new OperationNotSupportedException ("ERROR: No existe el profesor de esta tutoría.");
        }

        this.ITutorias.insertar(new Tutoria(profesor, tutoria.getNombre()));
    }
    
    @Override
    public void insertar(Sesion sesion) throws OperationNotSupportedException {
        if (sesion == null) {
            throw new NullPointerException ("ERROR: No se puede insertar una sesión nula.");
        }
        
        Tutoria tutoria = sesion.getTutoria();
        
        if (this.ITutorias.buscar(tutoria) == null) {
            throw new OperationNotSupportedException ("ERROR: No existe la tutoría de esta sesión.");
        }

        this.ISesiones.insertar(new Sesion (
                tutoria,
                sesion.getFecha(),
                sesion.getHoraInicio(),
                sesion.getHoraFin(),
                sesion.getMinutosDuracion()
        ));
    }
    
    @Override
    public void insertar(Cita cita) throws OperationNotSupportedException {
        if (cita == null) {
            throw new NullPointerException ("ERROR: No se puede insertar una cita nula.");
        }
        
        Sesion sesion = cita.getSesion();
        Alumno alumno = cita.getAlumno();
        
        if (cita == null) {
            throw new NullPointerException ("ERROR: No se puede insertar una sesión nula.");
        }
        
        if (this.IAlumnos.buscar(alumno) == null) {
            throw new OperationNotSupportedException ("ERROR: No existe el alumno de esta cita.");
        }
        
        if (this.ISesiones.buscar(sesion) == null) {
            throw new OperationNotSupportedException ("ERROR: No existe la sesión de esta cita.");
        }
        
        this.ICitas.insertar(new Cita (
                alumno,
                sesion,
                cita.getHora()
        ));
    }
    
    // Buscar
    @Override
    public Alumno buscar(Alumno alumno) {
        return this.IAlumnos.buscar(alumno);
    }
    
    @Override
    public Profesor buscar(Profesor profesor) {
        return this.IProfesores.buscar(profesor);
    }
    
    @Override
    public Tutoria buscar(Tutoria tutoria) {
        return this.ITutorias.buscar(tutoria);
    }
    
    @Override
    public Sesion buscar(Sesion sesion) {
        return this.ISesiones.buscar(sesion);
    }
    
    @Override
    public Cita buscar(Cita cita) {
        return this.ICitas.buscar(cita);
    }
    
    // Borrar
    @Override
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        List<Cita> citasDelAlumno = this.getCitas(alumno);
        
        for (int i = 0; i < citasDelAlumno.size(); i++) {
            this.borrar(citasDelAlumno.get(i));
        }
        
        this.IAlumnos.borrar(alumno);
    }
    
    @Override
    public void borrar(Profesor profesor) throws OperationNotSupportedException {
        List<Tutoria> tutoriasDelProfesor = this.getTutorias(profesor);
        
        for (int i = 0; i < tutoriasDelProfesor.size(); i++) {
            this.borrar(tutoriasDelProfesor.get(i));
        }
        
        this.IProfesores.borrar(profesor);
    }
    
    @Override
    public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
        List<Sesion> sesionesDeLaTutoria = this.getSesiones(tutoria);
        
        for (int i = 0; i < sesionesDeLaTutoria.size(); i++) {
            this.borrar(sesionesDeLaTutoria.get(i));
        }
        
        this.ITutorias.borrar(tutoria);
    }
    
    @Override
    public void borrar(Sesion sesion) throws OperationNotSupportedException {
        List<Cita> citasDeLaSesion = this.getCitas(sesion);
        
        for (int i = 0; i < citasDeLaSesion.size(); i++) {
            this.borrar(citasDeLaSesion.get(i));
        }
        
        this.ISesiones.borrar(sesion);
    }
    
    @Override
    public void borrar(Cita cita) throws OperationNotSupportedException {
        this.ICitas.borrar(cita);
    }
    
    // Leer
    @Override
    public List<Alumno> getAlumnos() {
        return this.IAlumnos.get();
    }
    
    @Override
    public List<Profesor> getProfesores() {
        return this.IProfesores.get();
    } 
    
    @Override
    public List<Tutoria> getTutorias() {
        return this.ITutorias.get();
    }
    
    @Override
    public List<Tutoria> getTutorias(Profesor profesor) {
        return this.ITutorias.get(profesor);
    }
    
    @Override
    public List<Sesion> getSesiones() {
        return this.ISesiones.get();
    }
    
    @Override
    public List<Sesion> getSesiones(Tutoria tutoria) {
        return this.ISesiones.get(tutoria);
    }
    
    @Override
    public List<Cita> getCitas() {
        return this.ICitas.get();
    }
    
    @Override
    public List<Cita> getCitas(Sesion sesion) {
        return this.ICitas.get(sesion);
    }
    
    @Override
    public List<Cita> getCitas(Alumno alumno) {
        return this.ICitas.get(alumno);
    }
}
