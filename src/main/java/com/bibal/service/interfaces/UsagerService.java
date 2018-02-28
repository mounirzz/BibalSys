package com.bibal.service.interfaces;

import java.util.List;

import com.bibal.metier.Usager;

public interface UsagerService {
	
	public void addUsager(String nom, String prenom, String adresse, String mail, String tel);
	public List<Usager> findAll();
	public Usager getById(Long idUsager);
	public List<Usager> searchByName(String nom);
	public void activer(Long idUsager);
	public Usager suspendre(Long idUsager);
	public Usager enAttenteDeCotisation(Long idUsager);
	public Usager update(Long idUsager, String nom, String prenom, String adresse, String tel, String mail);
	public List<Usager> getUsagersByEtat(String etat);
	
}
