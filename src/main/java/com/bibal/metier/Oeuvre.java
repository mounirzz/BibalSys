package com.bibal.metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.bibal.service.implementation.ExemplaireServiceImpl;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.util.EtatExemplaire;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Oeuvre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 740534677215234688L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long idOeuvre;
	private String nom;
	
	
	@OneToMany
	@JoinColumn(name="idOeuvre", referencedColumnName="idOeuvre")
	private List<Reservation> listeReservations;
	
	@OneToMany
	@JoinColumn(name="idOeuvre", referencedColumnName="idOeuvre")
	private List<Exemplaire> listeExemplaires;
	
	public Oeuvre() {
		// TODO Auto-generated constructor stub
	}

	public Oeuvre(String nom) {
		super();
		this.nom = nom;
		
	}
	
	public Oeuvre(Long idOeuvre, String nom) {
		super();
		this.idOeuvre = idOeuvre;
		this.nom = nom;
	}

	public Long getIdOeuvre() {
		return idOeuvre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Reservation> getListeReservations() {
		return listeReservations;
	}

	public List<Exemplaire> getListeExemplaires() {
		return listeExemplaires;
	}

	public List<Exemplaire> getExemplairesBons(){

		List<Exemplaire> listExemplairesBons = new ArrayList<>();
		for (Exemplaire exemplaire : listeExemplaires) {
			if(exemplaire.getEtatExemplaire().equals(EtatExemplaire.Bonne.toString()))
				listExemplairesBons.add(exemplaire);
				
		}
		return listExemplairesBons;
	}
	
	public void setListeExemplaires(List<Exemplaire> listeExemplaires) {
		this.listeExemplaires = listeExemplaires;
	}
	
	public void setListeExemplaires() {		
		ExemplaireService exemplaireService = new ExemplaireServiceImpl();
		listeExemplaires = exemplaireService.findByOeuvre(this);
	}
	
}
