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
import org.springframework.web.bind.annotation.RequestParam;

import com.bibal.metier.Usager;
import com.bibal.service.interfaces.UsagerService;

@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class UsagerController {

	@Autowired
	private UsagerService usagerService;

	@RequestMapping("/ListeUsagers")
	public String ListeUsagers(Model model) {
		List<Usager> usagers = usagerService.findAll();
		model.addAttribute("allUsagers", usagers);
		return "ListeUsagers";
	}

	@RequestMapping("/addUsager")
	public String ajouterUsager(@RequestParam("prenom") String prenom, @RequestParam("nom") String nom,
			@RequestParam("adresse") String adresse, @RequestParam("mail") String mail,
			@RequestParam("tel") String tel) {
		usagerService.addUsager(nom, prenom, adresse, mail, tel);
		return "redirect:ListeUsagers";
	}

	@RequestMapping("/updateUsager")
	public String updateUsager(@RequestParam("Id0") String id, @RequestParam("Prenom0") String prenom,
			@RequestParam("Nom0") String nom, @RequestParam("Adresse0") String adresse,
			@RequestParam("Mail0") String mail, @RequestParam("Telephone0") String tel) {
		usagerService.update(Long.valueOf(id), nom, prenom, adresse, tel, mail);
		return "redirect:ListeUsagers";
	}

	@RequestMapping(value = "/searchUsagerByName", method = RequestMethod.GET)
	public String searchUsagerByName(@RequestParam("nom") String nom, Model model) {
		List<Usager> usagers = usagerService.searchByName(nom);
		model.addAttribute("usagers", usagers);
		return "ListeUsagers";
	}

	@RequestMapping("/searchUsager")
	public String SearchUsager(Model model) {
		model.addAttribute("usagers", null);
		return "ListeUsagers";
	}

	@RequestMapping("/allUsagers")
	public String allUsagers(Model model) {
		List<Usager> usagers = usagerService.findAll();
		model.addAttribute("allUsagers", usagers);
		return "ListeUsagers";
	}
	
	@GetMapping("/activer")
	public String activer(Long idUsager) {
		usagerService.activer(idUsager);
		return "redirect:/ListeUsagers";
	}

}
