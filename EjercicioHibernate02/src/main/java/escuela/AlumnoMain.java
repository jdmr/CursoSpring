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

        AlumnoDao dao = (AlumnoDao) context.getBean("alumnoDaoHibernate");

        Salon salon = new Salon();
        salon.setNombre("sistemas");
        
        salon = dao.creaSalon(salon);

        Alumno alumno = new Alumno();
        alumno.setNombre("David");
        alumno.setApellido("Mendoza");
        alumno.setSalon(salon);
        alumno = dao.creaAlumno(alumno);
        System.out.println("El alumno ha sido creado con el id " + alumno.getId());

        Alumno prueba = dao.obtieneAlumno(alumno.getId());
        System.out.println("El nombre del alumno es " + prueba.getNombre());

        prueba.setNombre("Jorge");
        dao.actualizaAlumno(prueba);

        alumno = dao.obtieneAlumno(prueba.getId());
        System.out.println("El nombre del alumno ahora es " + alumno.getNombre());

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Dulce");
        alumno2.setApellido("Alvarado");
        alumno2.setSalon(salon);
        alumno2 = dao.creaAlumno(alumno2);
        
        System.out.println("Se ha creado alumno 2 con el id "+alumno2.getId());
        
        dao.eliminaSalon(salon);

    }
}
