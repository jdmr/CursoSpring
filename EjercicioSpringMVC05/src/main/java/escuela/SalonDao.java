package escuela;

import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
public class SalonDao {

    private static final Logger log = LoggerFactory.getLogger(SalonDao.class);
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Salon actualizaSalon(Salon salon) {
        currentSession().update(salon);
        return salon;
    }

    public Salon creaSalon(Salon salon) {
        currentSession().save(salon);
        return salon;
    }

    public Salon obtieneAlumno(Integer key) {
        return (Salon) currentSession().get(Salon.class, key);
    }

    public void eliminaSalon(Salon salon) {
        currentSession().refresh(salon);
        currentSession().delete(salon);
    }

    public Map<String, Object> lista(Map<String, Object> params) {
        Criteria criteria = currentSession().createCriteria(Salon.class);
        Criteria countCriteria = currentSession().createCriteria(Salon.class);
        List<Salon> salones;
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
            criteria.addOrder(Order.asc("nombre"));
        }
        criteria.setFirstResult(offset);
        criteria.setMaxResults(max);
        log.debug("BUSCANDO SALONES!!!");
        salones = criteria.list();
        log.debug("Salones {}", salones);

        countCriteria.setProjection(Projections.rowCount());
        List<Long> cantidades = countCriteria.list();
        cantidad = cantidades.get(0);
        log.debug("CANTIDAD: {}", cantidad);

        params.put("max", max);
        params.put("offset", offset);
        params.put("salones", salones);
        params.put("cantidad", cantidad);
        return params;
    }
}
