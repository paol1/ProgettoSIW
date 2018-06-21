package it.uniroma3.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Centro {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;

	@Column(nullable=false)
	private String nome;

	@Column
	private String email;

	@Column 
	private String numTelefono;
	
	@Column
	private String indirizzo;
	
	@Column
	private String capienza;

	public void setAttivitaList(List<Attivita> attivitaList) {
		this.attivitaList = attivitaList;
	}

	@OneToOne //ManyToOne?
	private Responsabile responsabile;
	
	
	@OneToMany
	@JoinColumn(name="centro_id")
	private List<Attivita> attivitaList;
	
	public Centro() {
		this.attivitaList = new ArrayList<Attivita>();
	}


	public Centro(String nome, String indirizzo, String email, String nTel, String capienza) {
		this.nome=nome;
		this.email=email;
		this.numTelefono=nTel;
		this.indirizzo=indirizzo;
		this.capienza=capienza;
		this.attivitaList =  new ArrayList<Attivita>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}


	public Responsabile getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(Responsabile responsabile) {
		this.responsabile = responsabile;
	}

	public String getCapienza() {
		return capienza;
	}


	public void setCapienza(String capienza) {
		this.capienza = capienza;
	}


	public List<Attivita> getAttivitaList() {
		return attivitaList;
	}

	public void setAttivita(List<Attivita> attivita) {
		this.attivitaList = attivita;
	}
	
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
