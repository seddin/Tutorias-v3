package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

public enum Opcion {
    INSERTAR_ALUMNO ("INSERTAR_ALUMNO") {
        @Override
        public void ejecutar () {
            vistaTexto.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO ("BUSCAR_ALUMNO") {
        @Override
        public void ejecutar () {
            vistaTexto.buscarAlumno();
        }
    },
    BORRAR_ALUMNO ("BORRAR_ALUMNO") {
        @Override
        public void ejecutar () {
            vistaTexto.borrarAlumno();
        }
    },
    LISTAR_ALUMNOS ("LISTAR_ALUMNOS") {
        @Override
        public void ejecutar () {
            vistaTexto.listarAlumnos();
        }
    },
    INSERTAR_PROFESOR ("INSERTAR_PROFESOR") {
        @Override
        public void ejecutar () {
            vistaTexto.insertarProfesor();
        }
    },
    BUSCAR_PROFESOR ("BUSCAR_PROFESOR") {
        @Override
        public void ejecutar () {
            vistaTexto.buscarProfesor();
        }
    },
    BORRAR_PROFESOR ("BORRAR_PROFESOR") {
        @Override
        public void ejecutar () {
            vistaTexto.borrarProfesor();
        }
    },
    LISTAR_PROFESORES ("LISTAR_PROFESORES") {
        @Override
        public void ejecutar () {
            vistaTexto.listarProfesores();
        }
    },
    INSERTAR_TUTORIA ("INSERTAR_TUTORIA") {
        @Override
        public void ejecutar () {
            vistaTexto.insertarTutoria();
        }
    },
    BUSCAR_TUTORIA ("BUSCAR_TUTORIA") {
        @Override
        public void ejecutar () {
            vistaTexto.buscarTutoria();
        }
    },
    BORRAR_TUTORIA ("BORRAR_TUTORIA") {
        @Override
        public void ejecutar () {
            vistaTexto.borrarTutoria();
        }
    },
    LISTAR_TUTORIAS ("LISTAR_TUTORIAS") {
        @Override
        public void ejecutar () {
            vistaTexto.listarTutorias();
        }
    },
    LISTAR_TUTORIAS_PROFESOR ("LISTAR_TUTORIAS_PROFESOR") {
        @Override
        public void ejecutar () {
            vistaTexto.listarTutoriasProfesor();
        }
    },
    INSERTAR_SESION ("INSERTAR_SESION") {
        @Override
        public void ejecutar () {
            vistaTexto.insertarSesion();
        }
    },
    BUSCAR_SESION ("BUSCAR_SESION") {
        @Override
        public void ejecutar () {
            vistaTexto.buscarSesion();
        }
    },
    BORRAR_SESION ("BORRAR_SESION") {
        @Override
        public void ejecutar () {
            vistaTexto.borrarSesion();
        }
    },
    LISTAR_SESIONES ("LISTAR_SESIONES") {
        @Override
        public void ejecutar () {
            vistaTexto.listarSesiones();
        }
    },
    LISTAR_SESIONES_TUTORIA ("LISTAR_SESIONES_TUTORIA") {
        @Override
        public void ejecutar () {
            vistaTexto.listarSesionesTutoria();
        }
    },
    INSERTAR_CITA ("INSERTAR_CITA") {
        @Override
        public void ejecutar () {
            vistaTexto.insertarCita();
        }
    },
    BUSCAR_CITA ("BUSCAR_CITA") {
        @Override
        public void ejecutar () {
            vistaTexto.buscarCita();
        }
    },
    BORRAR_CITA ("BORRAR_CITA") {
        @Override
        public void ejecutar () {
            vistaTexto.borrarCita();
        }
    },
    LISTAR_CITAS ("LISTAR_CITAS") {
        @Override
        public void ejecutar () {
            vistaTexto.listarCitas();
        }
    },
    LISTAR_CITAS_SESION ("LISTAR_CITAS_SESION") {
        @Override
        public void ejecutar () {
            vistaTexto.listarCitasSesion();
        }
    },
    LISTAR_CITAS_ALUMNO ("LISTAR_CITAS_ALUMNO") {
        @Override
        public void ejecutar () {
            vistaTexto.listarCitasAlumno();
        }
    },
    SALIR ("SALIR") {
        @Override
        public void ejecutar () {
            vistaTexto.terminar();
        }
    },
    MOSTRAR_MENU ("MOSTRAR_MENU") {
        @Override
        public void ejecutar () {
            Consola.mostrarMenu();
        }
    };
        
    
    private static VistaTexto vistaTexto;
    
    private String mensaje;
    
    private Opcion (String opcion) {
        this.mensaje = opcion;
    }
    
    public abstract void ejecutar ();
    
    // rombo amarillo ?
    public static void setVistaTexto(VistaTexto vistaTexto) {
        Opcion.vistaTexto = vistaTexto;
    }
    
    public static Opcion getOpcionSegunOrdinal (int opcion) {
        
        if (esOrdinalValido(opcion)) {
            return values()[opcion];
        }
        else {
            return null;
        }
    }
    
    public static boolean esOrdinalValido (int opcion) {
        if (opcion > values().length || opcion < 0) {
            return false;
        }
         
       return true;
    }
    
    @Override
    public String toString () {
        return ordinal() + ".- " + this.mensaje;
    }
}
