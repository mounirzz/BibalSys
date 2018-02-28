package com.bibal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bibal.metier.Exemplaire;
import com.bibal.metier.Magazine;
import com.bibal.metier.Oeuvre;
import com.bibal.metier.Usager;
import com.bibal.service.interfaces.EmpruntService;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.service.interfaces.MagazineService;
import com.bibal.service.interfaces.UsagerService;
import com.bibal.util.EtatExemplaire;
import com.bibal.util.EtatUsager;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class DetailMagazineController {

	@Autowired
	private MagazineService magazineService;

	@Autowired
	private EmpruntService empruntService;

	@Autowired
	private UsagerService usagerService;

	@Autowired
	private ExemplaireService exemplaireService;

	@GetMapping("/DetailMagazine")
	public String DetailMagazine(Model model, Long idMagazine) {
		try {
			Magazine magazine = magazineService.getById(idMagazine);
			model.addAttribute("magazine", magazine);
			List<Exemplaire> exemplaires = magazine.getExemplairesBons();
			String[] disponobilite = getDiponibiliteByExemplaire(exemplaires);
			List<Usager> usagers = usagerService.getUsagersByEtat(EtatUsager.Client.toString());
			model.addAttribute("exemplaires", exemplaires);
			model.addAttribute("nbrExempl", exemplaires.size());
			model.addAttribute("nbrExemplDispo", getNbrExemplaireDisponible(disponobilite));
			model.addAttribute("dispo", getDiponibiliteByExemplaire(exemplaires));
			model.addAttribute("usagers", usagers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "DetailMagazine";
	}

	private int getNbrExemplaireDisponible(String[] disponobilite) {
		int cmpt = 0;
		for (int i = 0; i < disponobilite.length; i++) {
			if (disponobilite[i].equals("Disponible"))
				cmpt++;
		}
		return cmpt;
	}

	private String[] getDiponibiliteByExemplaire(List<Exemplaire> exemplaires) {
		String[] disponobilite = new String[exemplaires.size()];
		int size = 0;
		for (Exemplaire exemplaire : exemplaires) {
			disponobilite[size] = empruntService.getDisponibilite(exemplaire.getIdExemplaire());
			size++;
		}
		for (int i = 0; i < disponobilite.length; i++)
			System.out.println(disponobilite[i]);
		return disponobilite;
	}

	@RequestMapping(value = "/updateEtatExemplaireM", method = RequestMethod.GET)
	public String updateEtatExemplaire(String etatExemplaire, Long idExemplaireU, Long idLivre) {
		exemplaireService.updateEtatExemplaire(etatExemplaire, idExemplaireU);
		return "redirect:/DetailMagazine?idMagazine=" + idLivre;
	}

	@RequestMapping(value = "/addExemplaireM", method = RequestMethod.GET)
	public String addExemplaire(Long idMagazine) {
		Oeuvre oeuvre = magazineService.getById(idMagazine);
		Exemplaire exemplaire = new Exemplaire(EtatExemplaire.Bonne.toString(), oeuvre);
		exemplaireService.addExemplaire(exemplaire);
		return "redirect:/DetailMagazine?idMagazine=" + idMagazine;
	}

}
