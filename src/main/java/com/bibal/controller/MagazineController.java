package com.bibal.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bibal.metier.Exemplaire;
import com.bibal.metier.Magazine;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.service.interfaces.MagazineService;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class MagazineController {

	@Autowired
	private ExemplaireService exemplaireService;

	@Autowired
	private MagazineService magazineService;

	@RequestMapping("/Magazines")
	public String Magazines(Model model) {
		List<Magazine> listeMagazines = magazineService.findAll();
		model.addAttribute("allMagazines", listeMagazines);
		return "Magazines";
	}

	@GetMapping("/addMagazines")
	public String allMagazines(String nom, String titre, int numeroDeSerie, String date, String theme,
			int nbrExemplaire) {
		Magazine magazine = magazineService.addMagazine(nom, theme, titre, date, numeroDeSerie);

		if (nbrExemplaire > 0) {
			for (int i = 0; i < nbrExemplaire; i++) {
				exemplaireService.addExemplaire(new Exemplaire("Bonne", magazine));
			}
		} else {
			exemplaireService.addExemplaire(new Exemplaire("Bonne", magazine));
		}
		return "redirect:/Magazines";
	}

	@GetMapping("/updateMagazine")
	public String update(Long idMagazine, String nom, String theme, String titre, String dateSortie,
			int numeroDeSerie) {
		magazineService.update(idMagazine, nom, theme, titre, dateSortie, numeroDeSerie);
		return "redirect:/DetailMagazine?idMagazine=" + idMagazine;
	}

}
