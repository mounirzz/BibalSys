package com.bibal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bibal.metier.Emprunt;
import com.bibal.metier.Exemplaire;
import com.bibal.metier.Reservation;
import com.bibal.metier.Usager;
import com.bibal.service.interfaces.EmpruntService;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.service.interfaces.ReservationService;
import com.bibal.service.interfaces.UsagerService;
import com.bibal.util.EtatUsager;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class EmpruntController {

	@Autowired
	private EmpruntService empruntService;

	@Autowired
	private ExemplaireService exemplaireService;

	@Autowired
	private UsagerService usagerService;

	@Autowired
	private ReservationService reservationService;

	@RequestMapping("/Emprunts")
	public String Emprunts(Model model) {
		List<Emprunt> emprunts = empruntService.getAllEmpruntsNonRendu();
		List<Exemplaire> exemplaires = exemplaireService.getExemplaireNonEmprunter();
		List<Usager> usagers = usagerService.getUsagersByEtat(EtatUsager.Client.toString());
		model.addAttribute("emprunts", emprunts);
		model.addAttribute("exemplaires", exemplaires);
		model.addAttribute("usagers", usagers);
		return "Emprunts";
	}

	@RequestMapping(value = "/addEmprunt", method = RequestMethod.GET)
	public String addEmprunt(Long idExemplaireA, Long idUsager, String page, Long idLivre) {
		empruntService.addEmprunt(idUsager, idExemplaireA);
		Reservation reservation = reservationService.searchByUsagerOeuvre(idUsager, idLivre);
		if (reservation != null) {
			reservationService.annulerReservation(reservation.getIdReservation());
		}

		if (page.equals("detailLivre")) {
			return "redirect:/DetailLivre?idLivre=" + idLivre;
		} else if (page.equals("detailMagazine")) {
			return "redirect:/DetailMagazine?idMagazine=" + idLivre;
		} else {
			return "redirect:/Emprunts";
		}

	}

	@RequestMapping(value = "/rendreM", method = RequestMethod.GET)
	public String rendreM(String etatExemplaire, Long idExemplaireR, Long idLivre) {
		empruntService.rendre(etatExemplaire, idExemplaireR);
		return "redirect:/DetailMagazine?idMagazine=" + idLivre;
	}

	@RequestMapping(value = "/rendreL", method = RequestMethod.GET)
	public String rendreL(String etatExemplaire, Long idExemplaireR, Long idLivre) {
		empruntService.rendre(etatExemplaire, idExemplaireR);
		return "redirect:/DetailLivre?idLivre=" + idLivre;
	}

	@RequestMapping(value = "/rendreEmp", method = RequestMethod.GET)
	public String rendreEmp(String etatExemplaire, Long idExemplaireR) {
		empruntService.rendre(etatExemplaire, idExemplaireR);
		return "redirect:/Emprunts";
	}

}
