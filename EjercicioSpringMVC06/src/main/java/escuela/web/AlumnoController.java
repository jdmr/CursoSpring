package escuela.web;

import escuela.dao.Alumno;
import escuela.dao.AlumnoDao;
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

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/alumno")
@Secured({"ROLE_ADMIN","ROLE_USER"})
public class AlumnoController {

    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
    @Autowired
    private AlumnoDao dao;

    @RequestMapping({"", "/lista"})
    public String lista(Map<String, Object> model) {
        log.debug("Mostrando lista de alumnos");
        List<Alumno> alumnos = dao.lista();
        if (alumnos.size() > 0) {
            model.put("alumnos", alumnos);
        }
        return "alumno/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        return "alumno/nuevo";
    }

    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public String crea(@Valid @ModelAttribute Alumno alumno, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "alumno/nuevo";
        }
        log.debug("Alumno: {}", alumno);
        
        dao.creaAlumno(alumno);
        return "redirect:/alumno/lista";
    }
}
