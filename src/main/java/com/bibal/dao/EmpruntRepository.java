package com.bibal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bibal.metier.Emprunt;


public interface EmpruntRepository extends JpaRepository<Emprunt, Long>
{

	@Query("select max(e) from Emprunt e where e.exemplaire.idExemplaire=:x")
	public Emprunt findByExemplaire(@Param("x") Long idExemplaire);
	
	@Query("select e from Emprunt e where e.dateRetourEffective=NULL")
	public List<Emprunt> getEmpruntsNonRendu();

	
}
