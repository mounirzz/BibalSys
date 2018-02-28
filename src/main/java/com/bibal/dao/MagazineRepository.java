package com.bibal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bibal.metier.Magazine;

public interface MagazineRepository extends JpaRepository<Magazine, Long>{

	@Query("select m from Magazine m where m.nom like %:x%")
	public List<Magazine> searchByName(@Param("x")String nom);
}
