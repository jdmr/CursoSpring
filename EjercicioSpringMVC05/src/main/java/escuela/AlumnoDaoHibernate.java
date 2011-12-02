package escuela;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    public void eliminaSalon(Salon salon) {
        currentSession().refresh(salon);
        currentSession().delete(salon);
    }

    public void eliminaAlumno(Alumno alumno) {
        currentSession().delete(alumno);
    }

    public Salon creaSalon(Salon salon) {
        currentSession().save(salon);
        return salon;
    }

    public Map<String, Object> lista(Map<String, Object> params) {
        Criteria criteria = currentSession().createCriteria(Alumno.class);
        Criteria countCriteria = currentSession().createCriteria(Alumno.class);
        List<Alumno> alumnos;
        Long cantidad;
        Integer max = 0;
        if (params.containsKey("max")) {
            max = (Integer) params.get("max");
        }
        Integer offset = 0;
        if (params.containsKey("offset")) {
            offset = (Integer) params.get("offset");
        }
        if (params.containsKey("order")) {
            if (params.containsKey("sort")) {
                if (params.get("sort").equals("asc")) {
                    criteria.addOrder(Order.asc((String) params.get("order")));
                } else {
                    criteria.addOrder(Order.desc((String) params.get("order")));
                }
            } else {
                criteria.addOrder(Order.asc((String) params.get("order")));
            }
        } else {
            criteria.addOrder(Order.desc("apellido"));
        }
        criteria.setFirstResult(offset);
        criteria.setMaxResults(max);
        alumnos = criteria.list();

        countCriteria.setProjection(Projections.rowCount());
        List<Long> cantidades = countCriteria.list();
        cantidad = cantidades.get(0);
        
        params.put("max", max);
        params.put("offset", offset);
        params.put("alumnos", alumnos);
        params.put("cantidad", cantidad);
        return params;
    }
}
