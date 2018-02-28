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
import org.springframework.web.bind.annotation.RequestMapping;

import com.bibal.metier.Livre;
import com.bibal.metier.Magazine;
import com.bibal.metier.Oeuvre;
import com.bibal.metier.Reservation;
import com.bibal.metier.Usager;
import com.bibal.service.interfaces.LivreService;
import com.bibal.service.interfaces.MagazineService;
import com.bibal.service.interfaces.ReservationService;
import com.bibal.service.interfaces.UsagerService;
import com.bibal.util.EtatUsager;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class ResevationController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private UsagerService usagerService;

	@Autowired
	private LivreService livreService;

	@Autowired
	private MagazineService magazineService;

	@RequestMapping("/Reservations")
	public String Reservations(Model model) {
		List<Reservation> reservations = reservationService.findAllReservationEnCours();
		List<Usager> usagers = usagerService.getUsagersByEtat(EtatUsager.Client.toString());
		List<Livre> livres = livreService.findAll();
		List<Magazine> magazines = magazineService.findAll();
		model.addAttribute("usagers", usagers);
		model.addAttribute("livres", livres);
		model.addAttribute("magazines", magazines);
		model.addAttribute("reservations", reservations);
		return "Reservations";
	}

	@GetMapping("/addReservationL")
	public String reserverL(Long idUsager, Long idLivre) {
		Usager usager = usagerService.getById(idUsager);
		Livre livre = livreService.getById(idLivre);
		reservationService.addReservation(new Date(), usager, livre);
		return "redirect:/DetailLivre?idLivre=" + idLivre;
	}

	@GetMapping("/addReservationM")
	public String reserverM(Long idUsager, Long idLivre) {
		Usager usager = usagerService.getById(idUsager);
		Magazine magazine = magazineService.getById(idLivre);
		reservationService.addReservation(new Date(), usager, magazine);
		return "redirect:/DetailMagazine?idMagazine=" + idLivre;
	}

	@GetMapping("/addReservationPage")
	public String reserverP(Long idUsager, Long idLivre) {
		Usager usager = usagerService.getById(idUsager);
		Livre livre = livreService.getById(idLivre);
		reservationService.addReservation(new Date(), usager, livre);
		return "redirect:/Reservations";
	}

	@GetMapping("/annulerReservation")
	public String annulerReservation(Long idReservation) {
		reservationService.annulerReservation(idReservation);
		return "redirect:/Reservations";
	}

}
