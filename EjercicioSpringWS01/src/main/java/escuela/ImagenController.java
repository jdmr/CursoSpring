package escuela;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/imagen")
public class ImagenController {

    private static final Logger log = LoggerFactory.getLogger(ImagenController.class);
    @Autowired
    private AlumnoDao dao;

    @RequestMapping("/{alumnoId}")
    public void mostrar(HttpServletResponse response, @PathVariable Integer alumnoId) {
        log.debug("Mostrando la imagen del alumno {}", alumnoId);
        Alumno alumno = dao.obtieneAlumno(alumnoId);
        byte[] imagen = alumno.getArchivo();
        if (imagen != null && imagen.length > 0) {
            response.setContentType(alumno.getTipoImagen());
            response.setContentLength(alumno.getTamanoImagen().intValue());

            OutputStream out = null;
            try {
                out = response.getOutputStream();
                out.write(imagen);
                out.flush();
            } catch (IOException e) {
                log.error("Error al intentar mostrar imagen", e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ex) {
                        log.error("No se pudo cerrar el outputstream", ex);
                    }
                }
            }
        }
    }
}
