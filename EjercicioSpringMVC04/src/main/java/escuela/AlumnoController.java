package escuela;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class AlumnoController {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
    
    @Autowired
    private AlumnoDao alumnoDao;
    
    @RequestMapping({"","/lista"})
    @Transactional(readOnly=true)
    public String lista(Map<String, Object> model) {
        log.debug("Entrando a la lista");
        List<Alumno> alumnos = alumnoDao.lista();
        if (alumnos != null && alumnos.size() > 0) {
            model.put("alumnos", alumnos);
        }
        return "alumno/lista";
    }
    
    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        log.debug("Mostrando pagina para crear un alumno");
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        return "alumno/nuevo";
    }
    
    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public String crea(@ModelAttribute Alumno alumno, Model model) {
        log.debug("Creando alumno");
        alumnoDao.creaAlumno(alumno);
        return "redirect:/alumno/lista";
    }
    
}
