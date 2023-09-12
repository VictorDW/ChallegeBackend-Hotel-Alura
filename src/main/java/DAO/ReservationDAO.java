package DAO;

import model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO extends JpaPersistence<Reservation> {

    List<Reservation> getByParameters(String reservationCod, LocalDate checkIn, LocalDate checkOut);

}
