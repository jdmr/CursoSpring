package escuela;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        
        AlumnoDao alumnoDao = context.getBean(AlumnoDao.class);
        alumnoDao.inicializa();
        
        NumberFormat nf = DecimalFormat.getInstance();
        NumberFormat nf2 = DecimalFormat.getInstance();
        
        nf.setMinimumIntegerDigits(4);
        nf.setGroupingUsed(false);
        nf2.setMinimumIntegerDigits(2);
        for(int i = 1; i <= 10; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Alumno").append(nf2.format(i));
            Alumno alumno = new Alumno(nf.format(i), sb.toString(), sb.toString());
            
            alumnoDao.crea(alumno);
        }
        
        List<Alumno> alumnos = alumnoDao.lista();
        for(Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
}
