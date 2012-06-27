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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    private AlumnoDao instance;

    public AlumnoDaoTest() {
    }

    @Before
    public void inicializa() {
        instance.inicializa();
    }

    /**
     * Test of lista method, of class AlumnoDao.
     */
    @Test
    public void debieraObtenerListaDeAlumnos() {
        System.out.println("lista");

        NumberFormat nf = DecimalFormat.getInstance();
        NumberFormat nf2 = DecimalFormat.getInstance();

        nf.setMinimumIntegerDigits(4);
        nf.setGroupingUsed(false);
        nf2.setMinimumIntegerDigits(2);
        for (int i = 1; i <= 10; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Alumno").append(nf2.format(i));
            Alumno alumno = new Alumno(nf.format(i), sb.toString(), sb.toString());

            instance.crea(alumno);
        }

        List<Alumno> result = instance.lista();

        assertNotNull(result);
        assertEquals(10, result.size());
    }

    /**
     * Test of obtiene method, of class AlumnoDao.
     */
    @Test
    public void debieraObtenerAlumno() {
        System.out.println("obtiene");
        String matricula = "0001";
        Alumno alumno = new Alumno(matricula, "Alumno 01", "Apellido 01");
        alumno = instance.crea(alumno);

        Alumno x = instance.obtiene(matricula);
        assertNotNull(x);
        assertEquals("Alumno 01", x.getNombre());

        Alumno y = instance.obtiene(alumno.getId());
        assertNotNull(y);
        assertEquals("Alumno 01", y.getNombre());
    }

    /**
     * Test of actualiza method, of class AlumnoDao.
     */
    @Test
    public void testActualiza() {
        System.out.println("actualiza");
        String matricula = "0001";
        Alumno alumno = new Alumno(matricula, "Alumno 01", "Apellido 01");
        alumno = instance.crea(alumno);

        alumno.setNombre("TEST");
        Alumno a = instance.actualiza(alumno);

        assertNotNull(a);

        Alumno b = instance.obtiene(matricula);
        assertNotNull(b);
        assertEquals("TEST", b.getNombre());
    }

    /**
     * Test of elimina method, of class AlumnoDao.
     */
    @Test(expected = EmptyResultDataAccessException.class)
    public void testEliminaConMatricula() {
        System.out.println("elimina");
        String matricula = "0001";
        Alumno alumno = new Alumno(matricula, "Alumno 01", "Apellido 01");
        instance.crea(alumno);

        instance.elimina(matricula);

        instance.obtiene(matricula);
        fail("Debió lanzar la excepción de lista vacía");
    }
    
    @Test(expected = EmptyResultDataAccessException.class)
    public void testEliminaConID() {
        System.out.println("elimina");
        String matricula = "0001";
        Alumno alumno = new Alumno(matricula, "Alumno 01", "Apellido 01");
        alumno = instance.crea(alumno);

        instance.elimina(alumno.getId());

        Alumno b = instance.obtiene(alumno.getId());
        fail("Debió lanzar la excepción de lista vacía");
    }
    
}
