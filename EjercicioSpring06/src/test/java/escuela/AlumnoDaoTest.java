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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:escuela.xml"})
@Transactional
public class AlumnoDaoTest {

    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoTest.class);
    @Autowired
    @Qualifier("alumnoDaoHibernate")
    private AlumnoDao instance;
    @Autowired
    private SessionFactory sessionFactory;
    
    public AlumnoDaoTest() {
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Test of lista method, of class AlumnoDao.
     */
    @Test
    public void testLista() {
        log.debug("Probando lista de Alumnos");
        log.debug("Preparando lista de datos");
        for(int i = 1; i <= 20; i++) {
            Alumno alumno;
            if (i < 10) {
                alumno = new Alumno("000"+i, "Nombre0"+i, "Apellido0"+i);
            } else {
                alumno = new Alumno("00"+i, "Nombre"+i, "Apellido"+i);
            }
            currentSession().save(alumno);
        }
        
        log.debug("Realizando prueba");
        List<Alumno> result = instance.lista();
        
        log.debug("Validando");
        assertNotNull(result);
        assertTrue(20 <= result.size());
    }

    /**
     * Test of obtiene method, of class AlumnoDao.
     */
    @Test
    public void debieraRegresarNullParaMatriculaInvalida() {
        log.debug("Debiera regresar null para matricula invalida");
        String matricula = "";
        Alumno result = instance.obtiene(matricula);
        assertNull(result);
    }

    @Test
    public void debieraRegresarAlumnoParaMatriculaValida() {
        log.debug("debiera regresar alumno para matricula valida");
        String matricula = "0001";
        Alumno alumno = new Alumno(matricula, "David", "Mendoza");
        currentSession().save(alumno);
        
        Alumno result = instance.obtiene(matricula);

        assertNotNull(result);
        assertEquals("David", result.getNombre());
    }

    /**
     * Test of crea method, of class AlumnoDao.
     */
    @Test(expected = AlumnoNuloException.class)
    public void debieraLanzarExceptionConAlumnoNulo() throws AlumnoNuloException {
        System.out.println("crea");
        Alumno alumno = null;
        instance.crea(alumno);
        fail("Debio lanzar la excepcion de alumno nulo");
    }
    
    @Test
    public void debieraCrearAlumno() throws AlumnoNuloException {
        System.out.println("Debiera crear alumno");
        Alumno alumno = new Alumno("0003","Héctor","Mendoza");
        Alumno result = instance.crea(alumno);
        assertNotNull(result);
        assertEquals("Héctor", result.getNombre());
        
        Alumno prueba = instance.obtiene("0003");
        assertNotNull(prueba);
        assertEquals("Héctor", prueba.getNombre());
    }

    /**
     * Test of actualiza method, of class AlumnoDao.
     */
    @Test
    public void testActualiza() {
        System.out.println("actualiza");
        Alumno alumno = new Alumno("0001", "David", "Mendoza");
        currentSession().save(alumno);
        
        assertFalse("Jorge David".equals(alumno.getNombre()));
        
        alumno.setNombre("Jorge David");
        Alumno result = instance.actualiza(alumno);
        
        currentSession().refresh(alumno);
        assertNotNull(result);
        assertEquals("Jorge David", alumno.getNombre());
    }

    /**
     * Test of elimina method, of class AlumnoDao.
     */
    @Test
    public void testElimina() {
        System.out.println("elimina");
        String matricula = "0001";
        String expResult = "0001";
        Alumno alumno = new Alumno(matricula, "David", "Mendoza");
        currentSession().save(alumno);
        
        String result = instance.elimina(matricula);
        assertEquals(expResult, result);
        
        assertNull(instance.obtiene(matricula));
    }
}
