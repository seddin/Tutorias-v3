package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones implements org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones {
    private static final String NOMBRE_FICHERO_SESIONES = "datos/sesiones.dat";

    private List<Sesion> sesiones;
    
    public Sesiones () {
        this.sesiones = new ArrayList<>();
    }

    @Override
    public void comenzar() {
        File fichero = new File(NOMBRE_FICHERO_SESIONES);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))) {
            Sesion sesion = null;
            do {
                sesion = (Sesion) entrada.readObject();
                insertar(sesion);
            } while (sesion != null);
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo encontrar la clase que tengo que leer.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo abrir el fichero de sesiones.");
        } catch (EOFException e) {
            System.out.println("Fichero sesiones leído satisfactoriamente.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void terminar() {
        File fichero = new File(NOMBRE_FICHERO_SESIONES);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero))){
            for (Sesion sesion : this.sesiones)
                salida.writeObject(sesion);
            System.out.println("Fichero sesiones escrito satisfactoriamente.");
        } catch (FileNotFoundException e) {
            System.out.println("No puedo crear el fichero de sesiones.");
        } catch (IOException e) {
            System.out.println("Error inesperado de Entrada/Salida.");
        }
    }
    
    @Override
    public List<Sesion> get() {
        Comparator<Profesor> profesoresComparator = Comparator.comparing(Profesor::getDni);
        Comparator<Tutoria> tutoriaComparator = Comparator.comparing(Tutoria::getProfesor, profesoresComparator).thenComparing(Tutoria::getNombre);
        Comparator<Sesion> sesionComparator = Comparator.comparing(Sesion::getTutoria, tutoriaComparator).thenComparing(Sesion::getFecha);
        
        List<Sesion> copiaOrdenada = this.copiaProfundaSesiones();
        copiaOrdenada.sort(sesionComparator);
        
        return copiaOrdenada;
    }
    
    private List<Sesion> copiaProfundaSesiones () {
        List<Sesion> copia = new ArrayList<>();
        
        for (int i = 0; i < this.sesiones.size(); i++) {
            copia.add(new Sesion (this.sesiones.get(i)));
        }
        
        return copia;
    }
    
    @Override
    public List<Sesion> get(Tutoria tutoria) {
        List<Sesion> copia = new ArrayList<>();
        
        if (tutoria == null) {
            throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
        }
        
        for (int i = 0; i < this.sesiones.size(); i++) {
            if (
                this.sesiones.get(i) != null &&
                this.sesiones.get(i).getTutoria().equals(tutoria)
            ) {
                copia.add(new Sesion (this.sesiones.get(i)));
            }
        }
        
        Comparator<Sesion> sesionComparator = Comparator.comparing(Sesion::getFecha);
        copia.sort(sesionComparator);
        
        return copia;
    }
    
    @Override
    public int getTamano() {
        return this.sesiones.size();
    }
    
    @Override
    public void insertar(Sesion sesion) throws OperationNotSupportedException {
        if (sesion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
        }
        
        if (this.buscar(sesion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
        }
        
        this.sesiones.add(new Sesion(sesion));
    }
    
    @Override
    public Sesion buscar(Sesion sesion) {
        if (sesion == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
        }

        for (int i = 0; i < this.sesiones.size(); i++) {
            if (this.sesiones.get(i).equals(sesion)) {
                return this.sesiones.get(i);
            }
        }
        
        return null;
    }
    
    @Override
    public void borrar(Sesion sesion) throws OperationNotSupportedException {
        if (sesion == null) {
            throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
        }
        
        if (this.buscar(sesion) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
        }
        
        for (int i = 0; i < this.sesiones.size(); i++) {
            if (this.sesiones.get(i).equals(sesion)) {
                this.sesiones.remove(i);
            }
        }
    }
}
