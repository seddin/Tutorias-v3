package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Citas implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas {
    private List<Cita> citas;
    
    public Citas () {
        this.citas = new ArrayList<>();
    }
    
    @Override
    public List<Cita> get() {
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getProfesor, profesoresComparator).thenComparing(Tutoria::getNombre);
        Comparator<Sesion> sesionComparator = Comparator.comparing(Sesion::getTutoria, tutoriaComparator).thenComparing(Sesion::getFecha);
        Comparator<Cita> citaComparator = Comparator.comparing(Cita::getSesion, sesionComparator).thenComparing(Cita::getHora);
                
        List<Cita> copiaOrdenada = this.copiaProfundaCitas();
        copiaOrdenada.sort(citaComparator);
        
        return copiaOrdenada;
    }
    
    private List<Cita> copiaProfundaCitas () {
        List<Cita> copia = new ArrayList<>();
        
        for (int i = 0; i < this.citas.size(); i++) {
            copia.add(new Cita (this.citas.get(i)));
        }
        
        return copia;
    }
    
    @Override
    public List<Cita> get(Sesion sesion) {
        List<Cita> copia = new ArrayList<>();
        
        if (sesion == null) {
            throw new NullPointerException("ERROR: La sesión no puede ser nula.");
        }
        
        for (int i = 0; i < this.citas.size(); i++) {
            if (
                this.citas.get(i) != null &&
                this.citas.get(i).getSesion().equals(sesion)
            ) {
                copia.add(new Cita (this.citas.get(i)));
            }
        }
        
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getProfesor, profesoresComparator).thenComparing(Tutoria::getNombre);
        Comparator<Sesion> sesionComparator = Comparator.comparing(Sesion::getTutoria, tutoriaComparator).thenComparing(Sesion::getFecha);
        Comparator<Cita> citaComparator = Comparator.comparing(Cita::getSesion, sesionComparator).thenComparing(Cita::getHora);
         
        
        copia.sort(citaComparator);
        
        return copia;
    }
    
    @Override
    public List<Cita> get(Alumno alumno) {
        List<Cita> copia = new ArrayList<>();
        
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        
        for (int i = 0; i < this.citas.size(); i++) {
            if (
                this.citas.get(i) != null &&
                this.citas.get(i).getAlumno().equals(alumno)
            ) {
                copia.add(new Cita (this.citas.get(i)));
            }
        }
        
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getProfesor, profesoresComparator).thenComparing(Tutoria::getNombre);
        Comparator<Sesion> sesionComparator = Comparator.comparing(Sesion::getTutoria, tutoriaComparator).thenComparing(Sesion::getFecha);
        Comparator<Cita> citaComparator = Comparator.comparing(Cita::getSesion, sesionComparator).thenComparing(Cita::getHora);
        
        
        copia.sort(citaComparator);
        
        return copia;
    }
    
    @Override
    public int getTamano() {
        return this.citas.size();
    }
    
    @Override
    public void insertar(Cita cita) throws OperationNotSupportedException {
        if (cita == null) {
            throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
        }
        
        if (this.buscar(cita) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
        }
        
        this.citas.add(new Cita(cita));
    }
    
    @Override
    public Cita buscar(Cita cita) {
         if (cita == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
        }
        
        for (int i = 0; i < this.citas.size(); i++) {
            if (this.citas.get(i).equals(cita)) {
                return new Cita(this.citas.get(i));
            }
        }
        
        return null;
    }
    
    @Override
    public void borrar(Cita cita) throws OperationNotSupportedException {
        if (cita == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
        }
        
        if (this.buscar(cita) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
        }
        
        for (int i = 0; i < this.citas.size(); i++) {
            if (this.citas.get(i).equals(cita)) {
                this.citas.remove(i);
            }
        }
    }
}
