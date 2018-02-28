package com.bibal.metier;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bibal.util.PropertiesManager;


@Entity
public class Magazine extends Oeuvre
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9064741753569733463L;

	private int delaiRetour = PropertiesManager.getDelai("delaiMagazine");
	private String theme;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateSortie;
	private int numeroDeSerie;

	public Magazine()
	{
		super();
		this.delaiRetour = PropertiesManager.getDelai("delaiMagazine");
	}

	public Magazine(String nom, String theme, String titre, Date dateSortie)
	{
		super(nom);
		this.delaiRetour = PropertiesManager.getDelai("delaiMagazine");
		this.theme = theme.toString();
		this.titre = titre;
		this.dateSortie = dateSortie;
	}

	public Magazine(String nom, String theme, String titre, Date dateSortie, int numeroDeSerie)
	{
		super(nom);
		this.delaiRetour = PropertiesManager.getDelai("delaiMagazine");
		this.theme = theme.toString();
		this.titre = titre;
		this.dateSortie = dateSortie;
		this.numeroDeSerie = numeroDeSerie;
	}

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getTitre()
	{
		return titre;
	}

	public void setTitre(String titre)
	{
		this.titre = titre;
	}

	public Date getDateSortie()
	{
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie)
	{
		this.dateSortie = dateSortie;
	}


	public int getNumeroDeSerie()
	{
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(int numeroDeSerie)
	{
		this.numeroDeSerie = numeroDeSerie;
	}

	public int getDelaiRetour()
	{
		return delaiRetour;
	}



}
