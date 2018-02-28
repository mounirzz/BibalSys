package com.bibal.metier;

import javax.persistence.Entity;

import com.bibal.util.PropertiesManager;

@Entity
public class Livre extends Oeuvre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8996466503212216697L;

	private String thematique;
	private String ecrivain;
	private int delaiRetour;

	public Livre() {
		super();
		this.delaiRetour = PropertiesManager.getDelai("delaiLivre");
	}

	public Livre(String nom, String thematique, String ecrivain) {
		super(nom);
		this.thematique = thematique;
		this.ecrivain = ecrivain;
		this.delaiRetour = PropertiesManager.getDelai("delaiLivre");
	}
	
	public String getThematique() {
		return thematique;
	}

	public void setThematique(String thematique) {
		this.thematique = thematique;
	}

	public String getEcrivain() {
		return ecrivain;
	}

	public void setEcrivain(String ecrivain) {
		this.ecrivain = ecrivain;
	}

	public int getDelaiRetour() {
		return delaiRetour;
	}
	
}
