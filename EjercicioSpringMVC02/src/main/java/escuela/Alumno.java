package escuela;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "ALUMNOS")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min=2,max=36,message="El nombre debe de contener al menos 2 letras y un maximo de 36")
    private String nombre;
    @Size(min=2,max=36,message="El apellido debe de contener al menos 2 letras y un maximo de 36")
    private String apellido;

    public Alumno() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
