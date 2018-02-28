package com.bibal.metier;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bibal.util.EtatExemplaire;


@Entity
//@Table(name="exemplaire")
public class Exemplaire
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExemplaire;
	private String etatExemplaire;

	@ManyToOne
	@JoinColumn(name = "idOeuvre")
	Oeuvre oeuvre;

	@OneToMany
	@JoinColumn(name = "idExemplaire", referencedColumnName = "idExemplaire")
	private List<Emprunt> listEmprunts;

	public Exemplaire()
	{
		this.etatExemplaire = EtatExemplaire.Bonne.toString();
	}

	public String getEtatExemplaire()
	{
		return etatExemplaire;
	}

	public void setEtatExemplaire(String etatExemplaire)
	{
		this.etatExemplaire = etatExemplaire;
	}

	public Oeuvre getOeuvre()
	{
		return oeuvre;
	}

	public void setOeuvre(Oeuvre oeuvre)
	{
		this.oeuvre = oeuvre;
	}

	public Long getIdExemplaire()
	{
		return idExemplaire;
	}

	public List<Emprunt> getListEmprunts()
	{
		return listEmprunts;
	}

	public Exemplaire(String etatExemplaire, Oeuvre oeuvre)
	{
		super();
		this.etatExemplaire = etatExemplaire;
		this.oeuvre = oeuvre;
	}



}
