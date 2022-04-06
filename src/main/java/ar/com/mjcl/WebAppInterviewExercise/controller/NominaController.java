package ar.com.mjcl.WebAppInterviewExercise.controller;

import ar.com.mjcl.WebAppInterviewExercise.configuration.IntegrationConfiguration;
import ar.com.mjcl.WebAppInterviewExercise.model.IntegrationResponse;
import ar.com.mjcl.WebAppInterviewExercise.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class NominaController {

    @Autowired
    private IntegrationService integrationService;

    @GetMapping
    public String getIndexPage(Model model, @ModelAttribute("cuit") String cuit){
        model.addAttribute("pageTitle", "Nómina de Escribanos");
        model.addAttribute("filterTitle", "Búsqueda");
        model.addAttribute("bodyTitle", "Resultado");
        model.addAttribute("noSearchMade", "No se realizo una busqueda aun.");
        model.addAttribute("noResultsFound", "No se encuentran resultados para la busqueda realizada.");
        model.addAttribute("errorIntegration", "No se logra consultar la nomina de escribanos");
        model.addAttribute("searchResult", 3);
        if(cuit != null && !cuit.isEmpty()){
            model.addAttribute("cuit", cuit);
            final IntegrationResponse ir = integrationService.getDatosNomina(cuit);
            addResponseToModel(model, ir);
        }
        return "index";
    }

    @GetMapping(path = "/filter/{cuit}")
    public RedirectView getResultsByCuit(@PathVariable("cuit") final String cuit, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("cuit", cuit);
        return new RedirectView("/");
    }

    private void addResponseToModel(final Model model, final IntegrationResponse ir) {
        if(ir.getResponseCode() == 200) {
            model.addAttribute("results", ir.getNominaList());
            model.addAttribute("searchResult", 0);
            return;
        }
        if(ir.getResponseCode() == 404 && ir.getResponseMessage().contains("No se encontro un escribano para la CUIT informada")){
            model.addAttribute("searchResult", 1);
            return;
        }
        model.addAttribute("searchResult", 2);
    }
}
