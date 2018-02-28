package com.bibal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bibal.metier.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select r from Reservation r where r.etatReservation='EnCours' order by r.date asc")
	public List<Reservation> getReservationsEnCours();

	@Query("select r from Reservation r where r.etatReservation='EnCours' and r.user.idUsager=:idUsager and r.oeuvre.idOeuvre=:idOeuvre")
	public List<Reservation> searchByUsagerOeuvre(@Param("idUsager") Long idUsager, @Param("idOeuvre") Long idOeuvre);
}
