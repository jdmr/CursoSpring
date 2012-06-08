/*
 * The MIT License
 *
 * Copyright 2012 Universidad de Montemorelos A. C.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package escuela.dao.impl;

import escuela.dao.AlumnoDao;
import escuela.model.Alumno;
import escuela.util.AlumnoNuloException;
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
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository("alumnoDaoHibernate")
@Transactional
public class AlumnoDaoHibernate implements AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoHibernate.class);
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Alumno> lista() {
        Query query = currentSession().createQuery("select a from Alumno a");
        return query.list();
    }

    @Override
    public Alumno obtiene(String matricula) {
        return (Alumno)currentSession().get(Alumno.class, matricula);
    }

    @Override
    public Alumno crea(Alumno alumno) throws AlumnoNuloException {
        try {
            currentSession().save(alumno);
            currentSession().flush();
        } catch(IllegalArgumentException e) {
            log.error("No se puede crear un alumno si esta nulo",e);
            throw new AlumnoNuloException();
        }
        return alumno;
    }

    @Override
    public Alumno actualiza(Alumno nuevo) {
        currentSession().update(nuevo);
        currentSession().flush();
        return nuevo;
    }

    @Override
    public String elimina(String matricula) {
        Alumno alumno = (Alumno) currentSession().load(Alumno.class, matricula);
        currentSession().delete(alumno);
        return matricula;
    }

    @Override
    public void inicializa() {
        // ya no lo vamos a ocupar con hibernate
    }
    
}
