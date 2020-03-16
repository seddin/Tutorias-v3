package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public class Alumnos implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos {
    private List<Alumno> alumnos;
    
    public Alumnos () {
        this.alumnos = new ArrayList<>();
    }
    
    @Override
    public List<Alumno> get() {
        Comparator<Alumno> alumnosComparator = Comparator.comparing(Alumno::getCorreo);
        
        List<Alumno> copiaOrdenada = this.copiaProfundaAlumnos();
        copiaOrdenada.sort(alumnosComparator);
        
        return copiaOrdenada;
    }
    
    private List<Alumno> copiaProfundaAlumnos () {
        List<Alumno> copia = new ArrayList<>();
        
        for (int i = 0; i < this.alumnos.size(); i++) {
            copia.add(new Alumno(this.alumnos.get(i)));
        }
        
        return copia;
    }
    
    @Override
    public int getTamano() {
        return this.alumnos.size();
    }
    
    @Override
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        
        if (this.buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
        }
        
        this.alumnos.add(new Alumno(alumno));
    }
    
    @Override
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
        }
        
        for (int i = 0; i < this.alumnos.size(); i++) {
            if (this.alumnos.get(i).equals(alumno)) {
                return new Alumno(this.alumnos.get(i));
            }
        }
        
        return null;
    }
    
    @Override
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
        }
        
        if (this.buscar(alumno) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
        }

        for (int i = 0; i < this.alumnos.size(); i++) {
            if (this.alumnos.get(i).equals(alumno)) {
                this.alumnos.remove(i);
            }
        }
    }
}
