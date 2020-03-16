package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public class Profesores implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores {
    private static final String NOMBRE_FICHERO_PROFESORES = "datos/profesores.dat";

    private List<Profesor> profesores;
    
    public Profesores () {
        this.profesores = new ArrayList<>();
    }

    @Override
    public void comenzar() {
        File fichero = new File(NOMBRE_FICHERO_PROFESORES);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))) {
            Profesor profesor = null;
            do {
                profesor = (Profesor) entrada.readObject();
                insertar(profesor);
            } while (profesor != null);
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo encontrar la clase que tengo que leer.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo abrir el fichero de profesores.");
        } catch (EOFException e) {
            System.out.println("Fichero profesores leído satisfactoriamente.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void terminar() {
        File fichero = new File(NOMBRE_FICHERO_PROFESORES);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero))){
            for (Profesor profesor : this.profesores)
                salida.writeObject(profesor);
            System.out.println("Fichero profesores escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de profesores.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        }
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
