package com.bibal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bibal.metier.Emprunt;
import com.bibal.metier.Exemplaire;


public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long>
{

	@Query("select e from Exemplaire e where e.oeuvre.idOeuvre=:x")
	public List<Exemplaire> findByOeuvre(@Param("x") Long idOeuvre);
	
	@Query("select e.exemplaire.idExemplaire from Emprunt e where e.dateRetourEffective=NULL")
	public List<Long> getIdsExemplaireEmprunter();

}
