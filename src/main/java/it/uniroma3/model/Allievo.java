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
public class Allievo {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable= false)
	private String nome;

	@Column(nullable= false)
	private String cognome;
	
	@Column(nullable=false)
	private String email;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataNascita;

	@Column(nullable=false)
	private String luogoNascita;

	@Column
	private String telefono;

	@ManyToMany(fetch=FetchType.EAGER, mappedBy="allievi")
	private List<Attivita> attivitaList;

//	@ManyToOne
//	private Centro centro;


	public Allievo() {
		this.attivitaList = new ArrayList<Attivita>();
	}	

	public Allievo(String nome, String cognome, String email, Date dataN, String luogoN, String telefono) {
		this.nome=nome;
		this.cognome=cognome;
		this.dataNascita=dataN;
		this.luogoNascita=luogoN;
		this.telefono=telefono;
		this.email=email;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	//	public void setCentro(Centro centro) {
	//		this.centro=centro;
	//	}
	
	public List<Attivita> getAttivitaList() {
		return attivitaList;
	}

	public void setAttivitaList(List<Attivita> attivitaList) {
		this.attivitaList = attivitaList;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
