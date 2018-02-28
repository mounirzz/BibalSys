package com.bibal.service.interfaces;

import java.util.Date;
import java.util.List;

import com.bibal.metier.Magazine;

public interface MagazineService {

	public Magazine addMagazine(String nom, String theme, String titre, String dateSortie, int numeroDeSerie);
	public List<Magazine> findAll();
	public Magazine getById(Long id);
	public List<Magazine> searchByName(String nom);
	public List<Magazine> searchByAuthor(String author);
	public List<Magazine> searchByThematique(String thematique);
	public void update(Long id, String nom,String theme, String titre, String dateSortie, int numeroDeSerie);
	
}
