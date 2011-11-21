package escuela;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jdmr
 */
@Repository
@Transactional
public class AlumnoDaoJpa implements AlumnoDao {
    
    @PersistenceContext
    private EntityManager em;

    public Alumno actualizaAlumno(Alumno alumno) {
        em.merge(alumno);
        em.flush();
        return alumno;
    }

    public Alumno creaAlumno(Alumno alumno) {
        em.persist(alumno);
        em.flush();
        return alumno;
    }

    public void inicializa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Alumno obtieneAlumno(Integer key) {
        return em.find(Alumno.class, key);
    }
    
}
