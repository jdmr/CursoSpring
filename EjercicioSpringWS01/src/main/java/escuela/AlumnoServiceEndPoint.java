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
public class AlumnoServiceEndPoint extends SpringBeanAutowiringSupport {

    private static final Logger log = LoggerFactory.getLogger(AlumnoServiceEndPoint.class);
    @Autowired
    private AlumnoDao alumnoDao;
    
    @WebMethod
    public Alumno obtieneAlumno(Integer alumnoId) {
        log.debug("Obteniendo el alumno {} por el servicio",alumnoId);
        return alumnoDao.obtieneAlumno(alumnoId);
    }
    
    @WebMethod
    public List<Alumno> lista() {
        log.debug("Obteniendo lista de alumnos por el servicio");
        return alumnoDao.lista();
    }
}
