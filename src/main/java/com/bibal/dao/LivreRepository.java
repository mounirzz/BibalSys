package com.bibal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibal.metier.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {

}
