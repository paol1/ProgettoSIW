package it.uniroma3.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;

public interface AttivitaRepository extends CrudRepository<Attivita, Long> {

	public List<Attivita> findByNome(String nome);
	
	public List<Attivita> findBydataOraAttivita(Date data);

	public List<Attivita> findByNomeAndDataOraAttivita(String nome, Date data);
	
//	public List<Allievo> findByAttivita(Attivita attivita);

}
