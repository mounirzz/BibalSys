package com.bibal.metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bibal.util.EtatExemplaire;

@Entity
public class Emprunt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmprunt;
	private Date date;
	private Date dateRetourEffective;
	
	@ManyToOne
	@JoinColumn(name="idUsager")
	private Usager usager;
	
	@ManyToOne
	@JoinColumn(name="idExemplaire")
	private Exemplaire exemplaire;		
	
	public Emprunt() {
		// TODO Auto-generated constructor stub
	}
	
	public Emprunt(Usager usager, Exemplaire exemplaire) {		
		this.usager=usager;
		this.exemplaire=exemplaire;
		this.date=new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateRetourEffective() {
		return dateRetourEffective;
	}

	public void setDateRetourEffective(Date dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}

	public Usager getUsager() {
		return usager;
	}

	public void setUsager(Usager usager) {
		this.usager = usager;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Long getIdEmprunt() {
		return idEmprunt;
	}


}
