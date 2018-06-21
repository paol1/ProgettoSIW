package it.uniroma3.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.controller.validator.AttivitaValidator;
import it.uniroma3.model.Attivita;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;

@Controller
public class AttivitaController {

	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private CentroService centroService;

	@Autowired
	private AttivitaValidator validator;

	@RequestMapping("/attivitaList")
	public String customers(Model model) {
		model.addAttribute("attivitaList", this.attivitaService.findAll());
		return "attivitaList";
	}
	
	@RequestMapping("/indice3")
	public String getIndice(Model model) {
		return "index";
	}

	@RequestMapping("/addAttivita")
	public String addAttivita(Model model) {
		model.addAttribute("attivita", new Attivita());
		
		return "attivitaForm";
	}


	@RequestMapping(value = "/attivita/{id}", method = RequestMethod.GET)
	public String getAllievo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("attivita", this.attivitaService.findById(id));
		return "showAttivita";
	}

	@RequestMapping(value ="/allievoList/{id}")
	public String getAllieviAttivita(@ModelAttribute("attivita") Attivita attivita, @PathVariable("id") Long id, Model model) {
		Attivita att = this.attivitaService.findById(id);
		model.addAttribute("allievi",att.getAllievi());
			return "attivitaAllievo";
	}

	@RequestMapping(value = "/attivita", method = RequestMethod.POST)
	public String newAttivita(@Valid @ModelAttribute("attivita") Attivita attivita, 
			Model model, BindingResult bindingResult) {
		this.validator.validate(attivita, bindingResult);

		if (this.attivitaService.alreadyExists(attivita)) {
			model.addAttribute("exists", "Attivita already exists");
			return "attivitaForm";
		}
		else {
			if (!bindingResult.hasErrors()) {
				this.attivitaService.save(attivita);
				model.addAttribute("attivitaList", this.attivitaService.findAll());
				return "attivitaList";
			}
		}
		return "attivitaForm";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ITALIAN);
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
