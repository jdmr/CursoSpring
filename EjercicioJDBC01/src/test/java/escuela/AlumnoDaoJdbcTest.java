package escuela;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jdmr
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:escuela.xml"})
public class AlumnoDaoJdbcTest {
    
    @Autowired
    private AlumnoDaoJdbc instance;
    
    public AlumnoDaoJdbcTest() {
    }

    /**
     * Test of creaAlumno method, of class AlumnoDaoJdbc.
     */
    @Test
    public void testCreaAlumno() {
        System.out.println("creaAlumno");
        Alumno alumno = new Alumno();
        alumno.setNombre("Hector");
        alumno.setApellido("Mendoza");
        Alumno result = instance.creaAlumno(alumno);
        
        assertNotNull(result.getId());
    }

}
