package it.uniroma3.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Attivita {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable= false)
	private String nome;

	@Column
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dataOraAttivita;
	
	

	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Allievo> allievi;
	
	public Attivita() {
		this.allievi = new ArrayList<Allievo>();
	};

	public Attivita(String nome, Date doa) {
		this.nome=nome;
		this.dataOraAttivita=doa;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataOraAttivita() {
		return dataOraAttivita;
	}

	public void setDataOraAttivita(Date dataOraAttivita) {
		this.dataOraAttivita = dataOraAttivita;
	}
	
	
	public List<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}



}
