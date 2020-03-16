package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public class Alumnos implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos {
    private static final String NOMBRE_FICHERO_AULUMNOS = "datos/alumnos.dat";

    private List<Alumno> alumnos;
    
    public Alumnos () {
        this.alumnos = new ArrayList<>();
    }

    @Override
    public void comenzar() {
        File ficheroAulumnos = new File(NOMBRE_FICHERO_AULUMNOS);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulumnos))) {
            Alumno alumno = null;
            do {
                alumno = (Alumno) entrada.readObject();
                insertar(alumno);
            } while (alumno != null);
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo encontrar la clase que tengo que leer.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo abrir el fihero de alumnos.");
        } catch (EOFException e) {
            System.out.println("Fichero alumnos leído satisfactoriamente.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void terminar() {
        File ficheroAlumnos = new File(NOMBRE_FICHERO_AULUMNOS);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAlumnos))){
            for (Alumno alumno : this.alumnos)
                salida.writeObject(alumno);
            System.out.println("Fichero aulas escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de alumnos.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        }
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
