package it.uniroma3;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;

@SpringBootApplication
public class SiwDemoSpringApplication {

	@Autowired
	private AllievoService allievoService; 
	@Autowired
	private AttivitaService attivitaService;
	@Autowired
	private CentroService centroService;
	
	public static void main(String[] args) {
		SpringApplication.run(SiwDemoSpringApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		Allievo allievo = new Allievo("Paolo", "Merialdo", "paolomerialdo@gmail.com", new Date(), "Genova","32133321");
		allievoService.save(allievo);
		for(Allievo a : allievoService.findByLuogoNascita("Genova")) {
			System.out.println(a.getNome());
		}
	}
	
	@PostConstruct
	public void init2() {
			Attivita attivita = new Attivita("Pilates", new Date());
			attivitaService.save(attivita);
			for(Attivita a : attivitaService.findByNome("Pilates")) {
				System.out.println(a.getNome());
			}
	}
			@PostConstruct
			public void init3() {
					Centro centro = new Centro("Centro1","Via francesco totti 10", "centro1Totti10@hotmail.it", "331221131","100");
					centroService.save(centro);
					for(Attivita a : attivitaService.findByNome("Pilates")) {
						System.out.println(a.getNome());
					}	
	
	}
	
		
	

	
		
}


