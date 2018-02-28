package com.bibal.service.implementation;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibal.dao.ReservationRepository;
import com.bibal.metier.Oeuvre;
import com.bibal.metier.Reservation;
import com.bibal.metier.Usager;
import com.bibal.service.interfaces.ReservationService;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService
{

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public void annulerReservation(Long idReservation)
	{
		Reservation reservation = reservationRepository.findOne(idReservation);
		reservation.annulerReservation();
	}

	@Override
	public void addReservation(Date date, Usager usager, Oeuvre oeuvre)
	{
		reservationRepository.save(new Reservation(date, usager, oeuvre));
	}

	@Override
	public List<Reservation> findAllReservationEnCours()
	{
		return reservationRepository.getReservationsEnCours();
	}

	@Override
	public Reservation searchByUsagerOeuvre(Long idUsager, Long idOeuvre) {
		return reservationRepository.searchByUsagerOeuvre(idUsager, idOeuvre).get(0);
	}
	


}
