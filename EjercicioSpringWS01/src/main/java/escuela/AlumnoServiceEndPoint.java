package escuela;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author jdmr
 */
@WebService(serviceName = "AlumnoService")
public class AlumnoServiceEndPoint extends SpringBeanAutowiringSupport implements AlumnoDao {

    private static final Logger log = LoggerFactory.getLogger(AlumnoServiceEndPoint.class);
    @Autowired
    private AlumnoDao alumnoDao;
    
    @WebMethod
    @Override
    public Alumno obtieneAlumno(Integer alumnoId) {
        log.debug("Obteniendo el alumno {} por el servicio",alumnoId);
        return alumnoDao.obtieneAlumno(alumnoId);
    }
    
    @WebMethod
    @Override
    public List<Alumno> lista() {
        log.debug("Obteniendo lista de alumnos por el servicio");
        return alumnoDao.lista();
    }

    @WebMethod
    @Override
    public Alumno actualizaAlumno(Alumno alumno) {
        return alumnoDao.actualizaAlumno(alumno);
    }

    @WebMethod
    @Override
    public Alumno creaAlumno(Alumno alumno) {
        return alumnoDao.creaAlumno(alumno);
    }

    @WebMethod
    @Override
    public void inicializa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
