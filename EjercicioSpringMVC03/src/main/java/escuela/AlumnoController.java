package escuela;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
    @Autowired
    private AlumnoDao dao;

    @Secured("ROLE_USER")
    @RequestMapping({"", "/lista"})
    public String lista(Map<String, Object> model) {
        log.debug("Mostrando lista de alumnos");
        List<Alumno> alumnos = dao.lista();
        if (alumnos.size() > 0) {
            model.put("alumnos", dao.lista());
        }
        return "alumno/lista";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        return "alumno/nuevo";
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public String crea(@Valid @ModelAttribute Alumno alumno, BindingResult bindingResult, @RequestParam(required=false) MultipartFile imagen) {
        if (bindingResult.hasErrors()) {
            return "alumno/nuevo";
        }
        log.debug("Alumno: {}", alumno);
        try {
            if (imagen != null) {
                alumno.setNombreImagen(imagen.getName());
                alumno.setTipoImagen(imagen.getContentType());
                alumno.setTamanoImagen(imagen.getSize());
                alumno.setArchivo(imagen.getBytes());
            }
        } catch(IOException e) {
            log.error("No se pudo crear el alumno debido a que tuvimos problemas con la imagen",e);
        }
        log.debug("Alumno: {}", alumno);
        
        dao.creaAlumno(alumno);
        return "redirect:/alumno/lista";
    }
}
