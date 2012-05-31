package escuela;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jdmr
 */
public class AlumnoMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("escuela.xml");

        AlumnoDao dao = (AlumnoDao) context.getBean("alumnoDaoJpa");

        Alumno alumno = new Alumno();
        alumno.setNombre("David");
        alumno.setApellido("Mendoza");
        alumno = dao.creaAlumno(alumno);
        System.out.println("El alumno ha sido creado con el id " + alumno.getId());

        Alumno prueba = dao.obtieneAlumno(alumno.getId());
        System.out.println("El nombre del alumno es " + prueba.getNombre());

        prueba.setNombre("Jorge");
        dao.actualizaAlumno(prueba);

        alumno = dao.obtieneAlumno(prueba.getId());
        System.out.println("El nombre del alumno ahora es " + alumno.getNombre());
    }
}
