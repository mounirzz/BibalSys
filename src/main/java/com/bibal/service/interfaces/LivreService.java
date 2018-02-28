package com.bibal.service.interfaces;

import java.util.List;

import com.bibal.metier.Livre;

public interface LivreService {

	public Livre addLivre(String nom, String thematique, String ecrivain);
	public List<Livre> findAll();
	public Livre getById(Long id);
	public List<Livre> searchByName(String nom);
	public List<Livre> searchByAuthor(String author);
	public List<Livre> searchByThematique(String thematique);
	public void update(Long id, String nom, String thematique, String ecrivain);
	
}
