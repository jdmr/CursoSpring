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
package escuela;

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
@Repository
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
        log.debug("Lista de alumnos");
        Query query = currentSession().createQuery("select a from Alumno a");
        return query.list();
    }

    @Override
    public Alumno obtiene(String matricula) throws AlumnoNoEncontradoException {
        Query query = currentSession().createQuery("select a from Alumno a where a.matricula = :matriculaParam");
        query.setString("matriculaParam", matricula);
        Alumno alumno = (Alumno) query.uniqueResult();
        if (alumno == null) {
            throw new AlumnoNoEncontradoException("No se encontro al alumno "+ matricula);
        }
        return alumno;
    }

    @Override
    public Alumno obtiene(Long id) throws AlumnoNoEncontradoException {
        Alumno alumno = (Alumno) currentSession().get(Alumno.class, id);
        if (alumno == null) {
            throw new AlumnoNoEncontradoException("No se encontro al alumno "+ id);
        }
        return alumno;
    }   

    @Override
    public Alumno crea(Alumno alumno) {
        currentSession().save(alumno);
        return alumno;
    }

    @Override
    public Alumno actualiza(Alumno alumno) {
        currentSession().update(alumno);
        return alumno;
    }

    @Override
    public void elimina(String matricula) throws AlumnoNoEncontradoException {
        Alumno alumno = this.obtiene(matricula);
        currentSession().delete(alumno);
    }

    @Override
    public void elimina(Long id) {
        Alumno alumno = (Alumno) currentSession().load(Alumno.class, id);
        currentSession().delete(alumno);
    }

    @Override
    public void inicializa() {
        //
    }
    
}
