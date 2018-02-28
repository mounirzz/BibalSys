package com.bibal.metier;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.bibal.util.EtatUsager;

@Entity
public class Usager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8071716128487923877L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsager;
	private String nom;
	private String prenom;	
	private String adresse;
	private String mail;
	private String tel;
	private int nombreSuspensions;
	private String etat;
	
	@OneToMany
	@JoinColumn(name="idUsager",referencedColumnName="idUsager")
	private List<Reservation> listReservations;
	
	@OneToMany
	@JoinColumn(name="idUsager", referencedColumnName="idUsager")
	private List<Emprunt> listEmprunts;
	
	public Usager() {
		super();
		etat = EtatUsager.Client.toString();
		nombreSuspensions = 0;
	}
	
	public Usager(String nom, String prenom, String adresse, String mail, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.adresse=adresse;
		this.etat = EtatUsager.Client.toString();
		this.nombreSuspensions = 0;
	}
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Long getIdUsager() {
		return idUsager;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public List<Emprunt> getListEmprunts() {
		return listEmprunts;
	}
	
	public List<Reservation> getListReservations() {
		return listReservations;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public int getNombreSuspensions() {
		return nombreSuspensions;
	}

	public void setNombreSuspensions(int nombreSuspensions) {
		this.nombreSuspensions = nombreSuspensions;
	}

	public void suspendre(){
		setEtat(EtatUsager.Suspendu.toString());
		incrementNombreSuspensions();
	}
	public void incrementNombreSuspensions(){
		if(nombreSuspensions<2)
			nombreSuspensions++;
		else{
			setEtat(EtatUsager.Desactive.toString());
			nombreSuspensions = 0;
		}
	}
	
}
