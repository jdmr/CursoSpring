package escuela;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author jdmr
 */
@Entity
@Table(name = "alumnos",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre", "apellido"})})
public class Alumno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    @ManyToOne(optional = false)
    private Salon salon;

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

    /**
     * @return the salon
     */
    public Salon getSalon() {
        return salon;
    }

    /**
     * @param salon the salon to set
     */
    public void setSalon(Salon salon) {
        this.salon = salon;
    }
}
