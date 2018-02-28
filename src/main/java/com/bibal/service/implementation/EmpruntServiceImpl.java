package com.bibal.service.implementation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibal.dao.EmpruntRepository;
import com.bibal.metier.Emprunt;
import com.bibal.metier.Exemplaire;
import com.bibal.metier.Livre;
import com.bibal.metier.Magazine;
import com.bibal.metier.Usager;
import com.bibal.service.interfaces.EmpruntService;
import com.bibal.service.interfaces.ExemplaireService;
import com.bibal.service.interfaces.UsagerService;
import com.bibal.util.EtatExemplaire;
import com.bibal.util.EtatUsager;


@Service
@Transactional
public class EmpruntServiceImpl implements EmpruntService
{

	@Autowired
	private EmpruntRepository empruntRepository;
	@Autowired
	private UsagerService usagerService;
	@Autowired
	private ExemplaireService exemplaireService;
	private Date date = new Date();

	@Override
	public String getDisponibilite(Long idExemplaire)
	{
		try
		{
			Emprunt emprunt = empruntRepository.findByExemplaire(idExemplaire);
			if (emprunt == null)
			{
				return "Disponible";
			}
			else
			{
				if (emprunt.getDateRetourEffective() == null)
					return "Emprunte";
				else
					return "Disponible";
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return "Disponible";
		}

	}

	@Override
	public void addEmprunt(Long idUsager, Long idExemplaire)
	{
		Usager usager = usagerService.getById(idUsager);
		Exemplaire exemplaire = exemplaireService.getById(idExemplaire);
		empruntRepository.save(new Emprunt(usager, exemplaire));
	}

	@Override
	public void rendre(String etatExemplaire, Long idExemplaire)
	{

		Emprunt emprunt = empruntRepository.findByExemplaire(idExemplaire);
		Exemplaire exemplaire = exemplaireService.getById(idExemplaire);
		Usager usager = usagerService.getById(emprunt.getUsager().getIdUsager());
		Calendar c = Calendar.getInstance();
		c.setTime(emprunt.getDate());
		if (!etatExemplaire.equals(EtatExemplaire.Bonne.toString()))
		{
			exemplaire.setEtatExemplaire(etatExemplaire);
			usager.suspendre();
		}
		if (exemplaire.getOeuvre() instanceof Livre)
		{
			c.add(c.DATE, ((Livre) exemplaire.getOeuvre()).getDelaiRetour());
			System.out.println(c.getTime());
			if (date.after(c.getTime()))
			{
				usager.suspendre();
			}
		}
		else if (exemplaire.getOeuvre() instanceof Magazine)
		{
			c.add(c.DATE, ((Magazine) exemplaire.getOeuvre()).getDelaiRetour());
			System.out.println(c.getTime());
			if (date.after(c.getTime()))
			{
				usager.suspendre();
			}
		}
		emprunt.setDateRetourEffective(date);
	}

	@Override
	public List<Emprunt> getAllEmpruntsNonRendu()
	{
		return empruntRepository.getEmpruntsNonRendu();
	}

}
