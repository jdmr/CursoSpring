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
@RequestMapping("/salon")
public class SalonController {

    private static final Logger log = LoggerFactory.getLogger(SalonController.class);
    @Autowired
    private SalonDao dao;

    @RequestMapping({"", "/lista"})
    public String lista(Map<String, Object> model) {
        log.debug("Mostrando lista de salones");
        dao.lista(model);
        List<Salon> salones = (List<Salon>)model.get("salones");
        log.debug("Salones {}",salones);
        System.out.println("Salones: "+salones);
        for(Salon salon : salones) {
            log.debug("Salon {}",salon);
        }
        return "salon/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model model) {
        Salon salon = new Salon();
        model.addAttribute("salon", salon);
        return "salon/nuevo";
    }

    @RequestMapping(value = "/crea", method = RequestMethod.POST)
    public String crea(@ModelAttribute Salon salon, Model model) {
        dao.creaSalon(salon);
        return "redirect:/salon/lista";
    }
}
