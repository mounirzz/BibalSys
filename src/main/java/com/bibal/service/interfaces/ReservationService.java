package com.bibal.service.interfaces;

import java.util.Date;
import java.util.List;

import com.bibal.metier.Oeuvre;
import com.bibal.metier.Reservation;
import com.bibal.metier.Usager;


public interface ReservationService
{

	public void annulerReservation(Long idReservation);

	public void addReservation(Date date, Usager usager, Oeuvre oeuvre);

	public List<Reservation> findAllReservationEnCours();
	
	public Reservation searchByUsagerOeuvre(Long idUsager, Long idOeuvre);
}
