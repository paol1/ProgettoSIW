package it.uniroma3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.CentroValidator;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;

@Controller
public class CentroController {

	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AttivitaService attivitaService;

	@Autowired
	private CentroValidator validator;

	@RequestMapping("/addCentro")
	public String addAllievo( Model model) {

		model.addAttribute("centro", new Centro());
		return "centroForm";
	}
	
	@RequestMapping("/indice")
	public String getIndice( Model model) {

		return "index";
	}
	
	@RequestMapping(value = "/centro", method = RequestMethod.POST)
	public String newAllievo(@Valid @ModelAttribute("centro") Centro centro, 
			Model model, BindingResult bindingResult) {
		this.validator.validate(centro, bindingResult);

		if (this.centroService.alreadyExists(centro)) {
			model.addAttribute("exists", "Centro already exists");
			return "showCentro";
		}
		else {
			if (!bindingResult.hasErrors()) {
				this.centroService.save(centro);
				model.addAttribute("centri", this.centroService.findAll());
				return "centroList";
			}
		}
		return "centroForm";
	}

	@RequestMapping("/centroList")
	public String centri(Model model) {
		model.addAttribute("centri", this.centroService.findAll());
		return "centroList";
	}

	@RequestMapping(value="/centro/{id}", method=RequestMethod.GET)
	public String getCentro(@ModelAttribute("centro") Centro centro, @PathVariable("id")Long id, Model model) {
		List<Attivita> activities = this.attivitaService.findAll();
		activities.removeAll(this.centroService.findById(id).getAttivitaList());
		Integer count = this.centroService.findById(id).getAttivitaList().size();
		model.addAttribute("count", count);
		model.addAttribute("activities", activities);
		model.addAttribute("centro", this.centroService.findById(id));
		return "showCentro";
	}
	
	@RequestMapping(value="/confermaAttivitaCentro/{id}")
	public String aggiungiAttivitaCentro(@ModelAttribute("centro") Centro centro, @PathVariable("id") Long id, Model model) {
		Long idatt = centro.getAttivitaList().get(centro.getAttivitaList().size()-1).getId();
		Attivita att = this.attivitaService.findById(idatt);
		centro = this.centroService.findById(id);
		centro.getAttivitaList().add(att);
		this.centroService.save(centro);
		return "index";
	}
	
	@RequestMapping(value="/mostraListaAttivitaCentro/{id}")
	public String showActivitiesCentro(@ModelAttribute("centro") Centro centro, @PathVariable("id") Long id, Model model) {
	 model.addAttribute("activities", this.centroService.findById(id).getAttivitaList());
	 return "centroAttivitaList";
	}
	

}
