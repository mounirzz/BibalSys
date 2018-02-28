package com.bibal.service.implementation;

import static org.assertj.core.api.Assertions.not;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibal.dao.ExemplaireRepository;
import com.bibal.metier.Exemplaire;
import com.bibal.metier.Oeuvre;
import com.bibal.service.interfaces.ExemplaireService;


@Service
@Transactional
public class ExemplaireServiceImpl implements ExemplaireService
{

	@Autowired
	private ExemplaireRepository exemplaireRepository;

	@Override
	public List<Exemplaire> findByOeuvre(Oeuvre oeuvre)
	{
		return exemplaireRepository.findByOeuvre(oeuvre.getIdOeuvre());
	}

	@Override
	public void addExemplaire(Exemplaire exemplaire)
	{
		exemplaireRepository.save(exemplaire);
	}

	@Override
	public Exemplaire getById(Long id)
	{
		return exemplaireRepository.findOne(id);
	}

	@Override
	public void updateEtatExemplaire(String etatExemplaire, Long idExemplaire)
	{
		Exemplaire exemplaire = exemplaireRepository.findOne(idExemplaire);
		exemplaire.setEtatExemplaire(etatExemplaire);
	}

	@Override
	public List<Exemplaire> getAllExemplaires()
	{
		return exemplaireRepository.findAll();
	}

	@Override
	public List<Exemplaire> getExemplaireNonEmprunter()
	{
		List<Long> ids = exemplaireRepository.getIdsExemplaireEmprunter();
		List<Exemplaire> exemplaires = exemplaireRepository.findAll();
		List<Exemplaire> exemplairesFinal = new ArrayList<>();
		for (Exemplaire exemplaire : exemplaires)
		{
			if (ids.indexOf(exemplaire.getIdExemplaire()) == -1)
			{
				exemplairesFinal.add(exemplaire);
			}
		}

		return exemplairesFinal;
	}

}
