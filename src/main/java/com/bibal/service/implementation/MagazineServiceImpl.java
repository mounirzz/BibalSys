package com.bibal.service.implementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibal.dao.MagazineRepository;
import com.bibal.metier.Magazine;
import com.bibal.service.interfaces.MagazineService;
import com.bibal.util.EtatReservation;

@Service
@Transactional
public class MagazineServiceImpl implements MagazineService {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	MagazineRepository magazineRepository;

	@Override
	public List<Magazine> findAll() {
		return magazineRepository.findAll();
	}

	@Override
	public Magazine getById(Long id) {
		return magazineRepository.findOne(id);
	}

	@Override
	public List<Magazine> searchByName(String nom) {
		return magazineRepository.searchByName(nom);
	}

	@Override
	public void update(Long id, String nom, String theme, String titre, String dateSortie, int numeroDeSerie) {

		System.out.println(id + " - " + nom + " - " + theme + " - " + dateSortie + " - " + numeroDeSerie);
		Magazine magazine = getById(id);
		System.out.println("--------------");
		System.out.println(magazine.getIdOeuvre());
		magazine.setNom(nom);
		magazine.setTheme(theme);
		magazine.setTitre(titre);
		try {
			magazine.setDateSortie(formatter.parse(dateSortie));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		magazine.setNumeroDeSerie(numeroDeSerie);
	}

	@Override
	public List<Magazine> searchByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Magazine> searchByThematique(String thematique) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Magazine addMagazine(String nom, String theme, String titre, String dateSortie, int numeroDeSerie) {
		Magazine magazine = null;
		try {
			magazine = magazineRepository
					.save(new Magazine(nom, theme, titre, formatter.parse(dateSortie), numeroDeSerie));
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException :" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return magazine;
	}

}
