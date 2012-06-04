package escuela;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("escuela.xml");

        System.out.println("INICIANDO");
        AlumnoDao dao = (AlumnoDao) context.getBean("alumnoDao");
        List<Alumno> alumnos = dao.lista();
        for(Alumno alumno : alumnos) {
            System.out.println("Alumno: "+alumno.getMatricula());
        }
        System.out.println("TERMINANDO");
    }
}
