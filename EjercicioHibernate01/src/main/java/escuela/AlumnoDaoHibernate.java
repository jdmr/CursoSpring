package escuela;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jdmr
 */
@Repository
@Transactional
public class AlumnoDaoHibernate implements AlumnoDao {

    private SessionFactory sessionFactory;
    
    @Autowired
    public AlumnoDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
}
