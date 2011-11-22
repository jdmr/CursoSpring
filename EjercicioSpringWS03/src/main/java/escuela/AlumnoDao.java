package escuela;

import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author jdmr
 */
@WebService
public interface AlumnoDao {

    Alumno actualizaAlumno(Alumno alumno);

    Alumno creaAlumno(final Alumno alumno);

    void inicializa();

    Alumno obtieneAlumno(Integer key);

    public List<Alumno> lista();
    
}
