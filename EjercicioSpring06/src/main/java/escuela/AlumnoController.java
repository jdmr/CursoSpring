/*
 * The MIT License
 *
 * Copyright 2012 Universidad de Montemorelos A. C.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package escuela;

import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
    @Autowired
    @Qualifier("alumnoDaoHibernate")
    private AlumnoDao alumnoDao;
    
    @RequestMapping
    public String lista(Model modelo) {
        log.debug("Buscando lista de alumnos");
        List<Alumno> alumnos = alumnoDao.lista();
        if (alumnos != null && alumnos.size() > 0) {
            modelo.addAttribute("alumnos", alumnos);
        }
        return "alumno/lista";
    }
    
    @RequestMapping("/nuevo")
    public String nuevo(Model modelo) {
        log.debug("Crear nuevo alumno");
        Alumno alumno = new Alumno();
        modelo.addAttribute("alumno", alumno);
        return "alumno/nuevo";
    }
    
    @RequestMapping("/crea")
    public String crea(@ModelAttribute Alumno alumno, 
        Model modelo,
        RedirectAttributes redirectAttributes) {
        try {
            alumno = alumnoDao.crea(alumno);
            StringBuilder sb = new StringBuilder();
            sb.append("El alumno ").append(alumno.getMatricula());
            sb.append(" ha sido creado");
            redirectAttributes.addFlashAttribute("mensaje", sb.toString());
            redirectAttributes.addFlashAttribute("estiloMensaje","alert-success");
        } catch (AlumnoNuloException ex) {
            log.error("No se pudo crear el alumno pues es nulo",ex);
            modelo.addAttribute("El alumno no puede ser nulo");
            modelo.addAttribute("estiloMensaje", "alert-error");
            return "/alumno/nuevo";
        }
        return "redirect:/alumno";
    }
    
    @RequestMapping("/edita/{matricula}")
    public String edita(@PathVariable String matricula, Model modelo) {
        Alumno alumno = alumnoDao.obtiene(matricula);
        modelo.addAttribute("alumno", alumno);
        return "alumno/edita";
    }
    
    @RequestMapping("/actualiza")
    public String actualiza(@ModelAttribute Alumno alumno,
        Model modelo,
        RedirectAttributes redirectAttributes) {
        alumnoDao.actualiza(alumno);
        return "redirect:/alumno";
    }
    
    @RequestMapping("/elimina/{matricula}")
    public String elimina(@PathVariable String matricula,
        Model modelo,
        RedirectAttributes redirectAttributes) {
        alumnoDao.elimina(matricula);
        return "redirect:/alumno";
    }
}
