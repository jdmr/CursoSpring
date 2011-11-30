package escuela;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jdmr
 */
public class EscuelaMain2 {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("escuela.xml");
        
        Escuela escuela = (Escuela) context.getBean("isar");
        
        Alumno alumno = new Alumno();
        alumno.setNombre("Dulce");
        alumno.setApellido("Alvarado");
        escuela.agregarAlumno(alumno);
        
        escuela.imprimirAlumnos();
    }
}
