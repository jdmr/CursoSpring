package escuela;

/**
 *
 * @author jdmr
 */
public interface AlumnoDao {

    Alumno actualizaAlumno(Alumno alumno);

    Alumno creaAlumno(final Alumno alumno);

    void inicializa();

    Alumno obtieneAlumno(Integer key);
    
    void eliminaAlumno(Alumno alumno);
    
}
