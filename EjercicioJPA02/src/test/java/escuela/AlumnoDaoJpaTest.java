package escuela;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jdmr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:escuela.xml"})
@Transactional
public class AlumnoDaoJpaTest {
    
    @Autowired
    private AlumnoDao instance;
    
    public AlumnoDaoJpaTest() {
    }

    /**
     * Test of creaAlumno method, of class AlumnoDaoJpa.
     */
    @Test
    public void testCreaAlumno() {
        System.out.println("creaAlumno");
        Alumno alumno = new Alumno();
        alumno.setNombre("Dulce");
        alumno.setApellido("Alvarado");
        Alumno result = instance.creaAlumno(alumno);
        assertNotNull(alumno.getId());
    }

}
