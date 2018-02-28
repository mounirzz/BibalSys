package com.bibal.metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bibal.util.EtatReservation;


@Entity
public class Reservation implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8608120265426737130L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReservation;
	private Date date;
	private String etatReservation;
	@ManyToOne
	@JoinColumn(name = "idUsager")
	private Usager user;
	@ManyToOne
	@JoinColumn(name = "idOeuvre")
	private Oeuvre oeuvre;

	public Reservation()
	{
		// TODO Auto-generated constructor stub
	}

	public Reservation(Date date)
	{
		super();
		this.date = date;
		this.etatReservation = EtatReservation.EnCours.toString();
	}

	public Reservation(Date date, Usager user, Oeuvre oeuvre)
	{
		super();
		this.date = date;
		this.user = user;
		this.oeuvre = oeuvre;
		this.etatReservation = EtatReservation.EnCours.toString();
	}

	public Long getIdReservation()
	{
		return idReservation;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void annulerReservation()
	{
		this.etatReservation = EtatReservation.Annulee.toString();
	}

	public Usager getUser()
	{
		return user;
	}

	public void setUser(Usager user)
	{
		this.user = user;
	}

	public Oeuvre getOeuvre()
	{
		return oeuvre;
	}

	public void setOeuvre(Oeuvre oeuvre)
	{
		this.oeuvre = oeuvre;
	}

	public void setIdReservation(Long idReservation)
	{
		this.idReservation = idReservation;
	}



}
