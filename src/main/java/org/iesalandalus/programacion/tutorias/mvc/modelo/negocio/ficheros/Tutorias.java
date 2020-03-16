package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class Tutorias implements ITutorias {
    private List<Tutoria> tutorias;
    
    public Tutorias () {
        this.tutorias = new ArrayList<>();
    }
    
    @Override
    public List<Tutoria> get() {
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getProfesor, profesoresComparator).thenComparing(Tutoria::getNombre);
        
        List<Tutoria> copiaOrdenada = this.copiaProfundaTutorias();
        copiaOrdenada.sort(tutoriaComparator);
        
        return copiaOrdenada;
    }
    
    private List<Tutoria> copiaProfundaTutorias () {
        List<Tutoria> copia = new ArrayList<>();
        
        for (int i = 0; i < this.tutorias.size(); i++) {
            copia.add(new Tutoria (this.tutorias.get(i)));
        }
        
        return copia;
    }
    
    @Override
    public List<Tutoria> get(Profesor profesor) {
        List<Tutoria> copia = new ArrayList<>();
        
        if (profesor == null) {
            throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
        }
        
        for (int i = 0; i < this.tutorias.size(); i++) {
            if (
                this.tutorias.get(i) != null &&
                this.tutorias.get(i).getProfesor().equals(profesor)
            ) {
                copia.add(new Tutoria (this.tutorias.get(i)));
            }
        }
        
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getNombre);
        copia.sort(tutoriaComparator);
        
        return copia;
    }
    
    @Override
    public int getTamano() {
        return this.tutorias.size();
    }
    
    @Override
    public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
        if (tutoria == null) {
            throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
        }
        
        if (this.buscar(tutoria) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
        }
        
        this.tutorias.add(new Tutoria (tutoria));
    }
    
    @Override
    public Tutoria buscar(Tutoria tutoria) {
        if (tutoria == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
        }
        
        for (int i = 0; i < this.tutorias.size(); i++) {
            if (this.tutorias.get(i).equals(tutoria)) {
                return new Tutoria (this.tutorias.get(i));
            }
        }
        
        return null;
    }
    
    @Override
    public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
        if (tutoria == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
        }
        
        if (this.buscar(tutoria) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
        }
        
        for (int i = 0; i < this.tutorias.size(); i++) {
            if (this.tutorias.get(i).equals(tutoria)) {
                this.tutorias.remove(i);
            }
        }
    }
}
