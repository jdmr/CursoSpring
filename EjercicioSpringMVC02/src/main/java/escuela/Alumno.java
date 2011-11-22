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
    private String nombreImagen;
    private String tipoImagen;
    private Long tamanoImagen;
    @Lob
    @Column(length=5000000)
    private byte[] archivo;

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
     * @return the nombreImagen
     */
    public String getNombreImagen() {
        return nombreImagen;
    }

    /**
     * @param nombreImagen the nombreImagen to set
     */
    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    /**
     * @return the tipoImagen
     */
    public String getTipoImagen() {
        return tipoImagen;
    }

    /**
     * @param tipoImagen the tipoImagen to set
     */
    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    /**
     * @return the tamanoImagen
     */
    public Long getTamanoImagen() {
        return tamanoImagen;
    }

    /**
     * @param tamanoImagen the tamanoImagen to set
     */
    public void setTamanoImagen(Long tamanoImagen) {
        this.tamanoImagen = tamanoImagen;
    }

    /**
     * @return the archivo
     */
    public byte[] getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", nombreImagen=" + nombreImagen + ", tipoImagen=" + tipoImagen + ", tamanoImagen=" + tamanoImagen + '}';
    }

}
