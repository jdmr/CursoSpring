package escuela;

import java.util.List;
import org.hibernate.Query;
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

    @Override
    public Alumno actualizaAlumno(Alumno alumno) {
        currentSession().update(alumno);
        return alumno;
    }

    @Override
    public Alumno creaAlumno(Alumno alumno) {
        currentSession().save(alumno);
        return alumno;
    }

    @Override
    public void inicializa() {
    }

    @Override
    public Alumno obtieneAlumno(Integer key) {
        return (Alumno) currentSession().get(Alumno.class, key);
    }

    @Override
    public void eliminaAlumno(Alumno alumno) {
        currentSession().delete(alumno);
    }
    
    @Override
    public List<Alumno> lista() {
        Query query = currentSession().createQuery("select a from Alumno a");
        return query.list();
    }
    
}
