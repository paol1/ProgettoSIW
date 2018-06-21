package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Centro;

public interface CentroRepository extends CrudRepository<Centro, Long>{
	
	public List<Centro> findByNome(String nome);
	
	public List<Centro> findByEmail(String email);
	
	public List<Centro> findByNomeAndIndirizzo(String nome, String indirizzo);

}
