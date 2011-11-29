package escuela;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdmr
 */
public class Escuela {
    private List<Alumno> alumnos;
    
    public Escuela() {
        alumnos = new ArrayList();
        Alumno alumno = new Alumno();
        alumno.setNombre("David");
        alumno.setApellido("Mendoza");
        alumnos.add(alumno);
    }
    
    public void imprimirAlumnos() {
        for(Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
    
    public void agregarAlumno(Alumno alumno) {
        alumnos.add(alumno);
        System.out.println("El alumno "+alumno+" ha sido agregado");
    }
}
