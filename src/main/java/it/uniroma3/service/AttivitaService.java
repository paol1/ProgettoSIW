package it.uniroma3.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.repository.AttivitaRepository;

@Transactional
@Service
public class AttivitaService {
	
	@Autowired
	private AttivitaRepository attivitarepository; 
	
	public Attivita save(Attivita attivita) {
		return this.attivitarepository.save(attivita);
	}

	public List<Attivita> findByNome(String nome) {
		return this.attivitarepository.findByNome(nome);
	}
	
	public List<Attivita> findBydataOraAttivita(Date data){
		return this.attivitarepository.findBydataOraAttivita(data);
	}

	public List<Attivita> findAll() {
		return (List<Attivita>) this.attivitarepository.findAll();
	}
	
//	public List<Allievo> findAllByAllievi(Attivita attivita){
//		return this.attivitarepository.findByAttivita(attivita);
//	}
	
	public Attivita findById(Long id) {
		Optional<Attivita> attivita = this.attivitarepository.findById(id);
		if (attivita.isPresent()) 
			return attivita.get();
		else
			return null;
	}

	public boolean alreadyExists(Attivita attivita) {
		List<Attivita> listaattivita = this.attivitarepository.findByNomeAndDataOraAttivita(attivita.getNome(), attivita.getDataOraAttivita());
		if (listaattivita.size() > 0)
			return true;
		else 
			return false;
	}	
}
