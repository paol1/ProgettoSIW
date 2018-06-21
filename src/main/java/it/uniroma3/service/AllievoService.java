package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.repository.AllievoRepository;

@Transactional
@Service
public class AllievoService {
	
	@Autowired
	private AllievoRepository allievorepository; 
	
	public Allievo save(Allievo allievo) {
		return this.allievorepository.save(allievo);
	}

	public List<Allievo> findByNome(String nome) {
		return this.allievorepository.findByNome(nome);
	}
	
	public List<Allievo> findByLuogoNascita(String email) {
		return this.allievorepository.findByEmail(email);
	}
	
	public List<Allievo> findByNomeAndCognomeAndLuogoNascita(String luogo, String nome, String cognome) {
		return this.allievorepository.findByNomeAndCognomeAndLuogoNascita(nome, cognome, luogo);
	}


	public List<Allievo> findAll() {
		return (List<Allievo>) this.allievorepository.findAll();
	}
	
	public Allievo findById(Long id) {
		Optional<Allievo> allievo = this.allievorepository.findById(id);
		if (allievo.isPresent()) 
			return allievo.get();
		else
			return null;
	}

	public boolean alreadyExists(Allievo allievo) {
		List<Allievo> allievi = this.allievorepository.findByNomeAndCognomeAndLuogoNascita(allievo.getNome(), allievo.getCognome(), allievo.getLuogoNascita());
		if (allievi.size() > 0)
			return true;
		else 
			return false;
	}	
}
