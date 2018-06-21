package it.uniroma3.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import it.uniroma3.controller.validator.AllievoValidator;
import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;

@Controller
public class AllievoController {


	@Autowired
	private AllievoService allievoService;
	@Autowired
	private AttivitaService attivitaService;

	@Autowired
	private AllievoValidator validator;

	@RequestMapping("/allievi")
	public String customers(Model model) {
		model.addAttribute("allievi", this.allievoService.findAll());
		return "allievoList";
	}
	
	@RequestMapping("/indice2")
	public String getIndice(Model model) {
		return "index";
	}

	@RequestMapping("/addAllievo")
	public String addAllievo( Model model) {

		model.addAttribute("allievo", new Allievo());
		//        List<Attivita> test = new ArrayList<>();
		//        model.addAttribute("test", test);
		//        List<Attivita> tests = attivitaService.findAll();
		//        model.addAttribute("tests", tests);  


		return "allievoForm";
	}

	@RequestMapping(value="/mostraListaAttivita/{id}")
	public String showActivities(@ModelAttribute("allievo") Allievo allievo, @PathVariable("id") Long id, Model model) {
		model.addAttribute("activities", this.allievoService.findById(id).getAttivitaList());
		return "allievoAttivitaList";
	}

	@RequestMapping(value="/confermaAllievoAttivita/{id}") 
	public String nuovaPartecipazione(@ModelAttribute("allievo") Allievo allievo, 
			@PathVariable("id") Long id, Model model){
		Long idatt = allievo.getAttivitaList().get(allievo.getAttivitaList().size()-1).getId();
		Attivita att = this.attivitaService.findById(idatt);
		allievo = this.allievoService.findById(id);
		allievo.getAttivitaList().add(att);
		att.getAllievi().add(allievo);

		this.allievoService.save(allievo);
		this.attivitaService.save(att);
		return "index";

	}


	@RequestMapping(value = "/allievo/{id}", method = RequestMethod.GET)
	public String getAllievo(@ModelAttribute("allievo") Allievo allievo, @PathVariable("id") Long id, Model model, Attivita attivita) {
		List<Attivita> activities = this.attivitaService.findAll();
		activities.removeAll(this.allievoService.findById(id).getAttivitaList());
		Integer count = this.allievoService.findById(id).getAttivitaList().size();
		model.addAttribute("count", count);
		model.addAttribute("activities", activities);
		model.addAttribute("allievo", this.allievoService.findById(id));

		return "showAllievo";
	}

	@RequestMapping(value = "/allievo", method = RequestMethod.POST)
	public String newAllievo(@Valid @ModelAttribute("allievo") Allievo allievo, 
			Model model, BindingResult bindingResult) {
		this.validator.validate(allievo, bindingResult);

		if (this.allievoService.alreadyExists(allievo)) {
			model.addAttribute("exists", "Allievo already exists");
			return "showAllievo";
		}
		else {
			if (!bindingResult.hasErrors()) {
				this.allievoService.save(allievo);
				model.addAttribute("allievi", this.allievoService.findAll());
				return "allievoList";
			}
		}
		return "allievoForm";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	//    <td><select>
	//	<option value="0">Select Test Order</option>
	//	<option th:each="attivita : ${tests}" th:value="${attivita.id}"
	//		th:text="${attivita.id}+' : '+${attivita.nome}"></option>
	//</select></td>

}
