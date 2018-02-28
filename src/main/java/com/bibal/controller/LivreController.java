package com.bibal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bibal.metier.Exemplaire;
import com.bibal.metier.Livre;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.service.interfaces.LivreService;


@Configuration
@Controller
@EnableAutoConfiguration
@ComponentScan
public class LivreController
{

	@Autowired
	private LivreService livreService;
	@Autowired
	private ExemplaireService exemplaireService;

	@RequestMapping("/Livres")
	public String Livre(Model model)
	{
		List<Livre> listeLivres = livreService.findAll();
		model.addAttribute("allLivres", listeLivres);
		return "Livres";
	}

	@RequestMapping("/allLivres")
	public String allLivre(Model model)
	{
		//		Livre livre = new Livre("nom", "thematique", "ecrivain");
		//		Livre livre2 = new Livre("nom2", "thematique", "ecrivain");
		//		List<Livre> listeLivres = new ArrayList<Livre>();
		//		listeLivres.add(livre);
		//		listeLivres.add(livre2);
		List<Livre> listeLivres = livreService.findAll();
		model.addAttribute("allLivres", listeLivres);
		return "Livres";
	}


	@RequestMapping("/addLivre")
	public String addLivre(@RequestParam("nom") String nom, @RequestParam("auteur") String auteur,
			@RequestParam("thematique") String thematique, @RequestParam("nbrExemplaire") int nbrExemplaire)
	{
		Livre livre = livreService.addLivre(nom, thematique, auteur);
		if (nbrExemplaire > 0)
		{
			for (int i = 0; i < nbrExemplaire; i++)
			{
				exemplaireService.addExemplaire(new Exemplaire("Bonne", livre));
			}
		}
		else
		{
			exemplaireService.addExemplaire(new Exemplaire("Bonne", livre));
		}

		return "redirect:DetailLivre?idLivre=" + livre.getIdOeuvre();
	}

	@PostMapping("/updateLivre")
	public String updateLivre(Long idLivre, String nom, String thematique, String auteur)
	{
		livreService.update(idLivre, nom, thematique, auteur);
		return "redirect:/DetailLivre?idLivre=" + idLivre;
	}

}
