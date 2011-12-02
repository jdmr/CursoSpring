package escuela;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping({"", "/lista"})
    public String lista(Map<String, Object> model) {
        log.debug("Mostrando lista de alumnos");
        dao.lista(model);
        return "alumno/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        return "alumno/nuevo";
    }

    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public String crea(@ModelAttribute Alumno alumno, Model model) {
        dao.creaAlumno(alumno);
        return "redirect:/alumno/lista";
    }
}
