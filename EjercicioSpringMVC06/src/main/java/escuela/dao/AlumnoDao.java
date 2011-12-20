package escuela.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jdmr
 */
@Repository
@Transactional
public class AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDao.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Alumno actualizaAlumno(Alumno alumno) {
        currentSession().update(alumno);
        return alumno;
    }

    public Alumno creaAlumno(Alumno alumno) {
        currentSession().save(alumno);
        return alumno;
    }

    public void inicializa() {
    }

    public Alumno obtieneAlumno(Integer key) {
        return (Alumno) currentSession().get(Alumno.class, key);
    }

    public void eliminaAlumno(Alumno alumno) {
        currentSession().delete(alumno);
    }
    
    public List<Alumno> lista() {
        log.debug("Obteniendo lista de alumnos");
        Query query = currentSession().createQuery("select a from Alumno a");
        return query.list();
    }
    
}
