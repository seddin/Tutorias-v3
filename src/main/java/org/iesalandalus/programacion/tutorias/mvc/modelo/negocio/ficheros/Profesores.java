package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores {
    private List<Profesor> profesores;
    
    public Profesores () {
        this.profesores = new ArrayList<>();
    }
    
    @Override
    public List<Profesor> get() {
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        
        List<Profesor> copiaOrdenada = this.copiaProfundaProfesores();
        copiaOrdenada.sort(profesoresComparator);
        
        return copiaOrdenada;
    }
    
    private List<Profesor> copiaProfundaProfesores () {
        List<Profesor> copia = new ArrayList<>();
        
        for (int i = 0; i < this.profesores.size(); i++) {
            copia.add(new Profesor(this.profesores.get(i)));
        }
        
        return copia;
    }
    
    @Override
    public int getTamano() {
        return this.profesores.size();
    }
    
    @Override
    public void insertar(Profesor profesor) throws OperationNotSupportedException {
        if (profesor == null) {
            throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
        }
        
        if (this.buscar(profesor) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
        }
        
        this.profesores.add(new Profesor (profesor));
    }
    
    @Override
    public Profesor buscar(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
        }

        for (int i = 0; i < this.profesores.size(); i++) {
            if (this.profesores.get(i).equals(profesor)) {
                return new Profesor(this.profesores.get(i));
            }
        }
        
        return null;
    }
    
    @Override
    public void borrar(Profesor profesor) throws OperationNotSupportedException {
        if (profesor == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
        }
        
        if (this.buscar(profesor) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
        }
        
        for (int i = 0; i < this.profesores.size(); i++) {
            if (this.profesores.get(i).equals(profesor)) {
                this.profesores.remove(i);
            }
        }
    }
}
