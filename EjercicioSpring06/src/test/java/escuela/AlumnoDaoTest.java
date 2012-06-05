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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:escuela.xml"})
public class AlumnoDaoTest {

    @Autowired
    private AlumnoDao instance;

    public AlumnoDaoTest() {
    }
    
    @Before
    public void inicializaLista() {
        instance.inicializa();
    }

    /**
     * Test of lista method, of class AlumnoDao.
     */
    @Test
    public void testLista() {
        System.out.println("lista");
        List<Alumno> result = instance.lista();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("0001", result.get(0).getMatricula());
    }

    /**
     * Test of obtiene method, of class AlumnoDao.
     */
    @Test
    public void debieraRegresarNullParaMatriculaInvalida() {
        System.out.println("obtiene");
        String matricula = "";
        Alumno result = instance.obtiene(matricula);
        assertNull(result);
    }

    @Test
    public void debieraRegresarAlumnoParaMatriculaValida() {
        System.out.println("debier regresar alumno para matricula valida");
        String matricula = "0001";
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
        Alumno nuevo = instance.obtiene("0001");
        assertFalse("Jorge David".equals(nuevo.getNombre()));
        
        nuevo.setNombre("Jorge David");
        Alumno result = instance.actualiza(nuevo);
        assertNotNull(result);
        assertEquals("Jorge David", result.getNombre());
    }

    /**
     * Test of elimina method, of class AlumnoDao.
     */
    @Test
    public void testElimina() {
        System.out.println("elimina");
        String matricula = "0001";
        String expResult = "0001";
        String result = instance.elimina(matricula);
        assertEquals(expResult, result);
        
        assertNull(instance.obtiene("0001"));
    }
}
