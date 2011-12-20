package escuela.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/secure")
public class SecureController {

    private static final Logger log = LoggerFactory.getLogger(SecureController.class);

    @RequestMapping
    public String hola(Model model) {
        log.debug("Mostrando hola seguro");
        model.addAttribute("hola", "Hola mundo seguro!!!");
        return "secure/hola";
    }
}
