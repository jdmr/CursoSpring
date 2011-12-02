package escuela;

import java.util.Map;

/**
 *
 * @author jdmr
 */
public interface AlumnoDao {

    Alumno actualizaAlumno(Alumno alumno);

    Alumno creaAlumno(final Alumno alumno);

    void inicializa();

    Alumno obtieneAlumno(Integer key);
    
    Salon creaSalon(final Salon salon);
    
    void eliminaAlumno(Alumno alumno);
    
    void eliminaSalon(Salon salon);
    
    Map<String,Object> lista(Map<String, Object> params);
    
}
