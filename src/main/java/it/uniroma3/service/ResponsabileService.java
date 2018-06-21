package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Responsabile;
import it.uniroma3.repository.ResponsabileRepository;

@Transactional
@Service
public class ResponsabileService {
	
	@Autowired
	 private ResponsabileRepository responsabileRepository;
	
	public Responsabile save(Responsabile responsabile) {
		return this.responsabileRepository.save(responsabile);
	}
	
	public List<Responsabile> findByNomeAndCognome(String nome, String cognome) {
		return this.responsabileRepository.findByNomeAndCognome(nome, cognome);
	}
	
	public List<Responsabile> findAll() {
		return (List<Responsabile>) this.responsabileRepository.findAll();
	}
	
	public Responsabile findById(Long id) {
		Optional<Responsabile> resp = this.responsabileRepository.findById(id);
		if(resp.isPresent())
			return resp.get();
		else
			return null;
	}
	
	public boolean alreadyExists(Responsabile responsabile) {
		 List<Responsabile> responsabili = this.responsabileRepository.findByNomeAndCognome(responsabile.getNome(),  responsabile.getCognome());
		 if(responsabili.size()>0)
			 return true;
		 else
			 return false;
	}

}
