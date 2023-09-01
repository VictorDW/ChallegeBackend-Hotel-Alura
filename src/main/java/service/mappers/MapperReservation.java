package service.mappers;

import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;
import modelo.Guest;
import modelo.Reservation;
import modelo.Status;

import java.math.BigDecimal;

public final class MapperReservation {

    public static  Reservation mapperReservationRequestDTOToReservation(ReservationRequestDTO reservationRequestDTO,
                                                                                                            Guest guest) {

        Reservation reservation = new Reservation();

        reservation.setReservationCod(reservationRequestDTO.getReservationCod());
        reservation.setCheckIn(reservationRequestDTO.getCheckIn());
        reservation.setCheckOut(reservationRequestDTO.getCheckOut());
        reservation.setCostOfReservation(reservationRequestDTO.getCost());
        reservation.setMethodPayment(reservationRequestDTO.getMethodPayment());
        reservation.setStatus(Status.Activa);
        reservation.setGuest(guest);

        return reservation;
    }
    public static ReservationDTO mapperReservationToReservationDTO(Reservation reservation) {

        return new ReservationDTO (
                reservation.getId(),
                reservation.getReservationCod(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getCostOfReservation(),
                reservation.getMethodPayment(),
                reservation.getStatus().toString(),
                reservation.getGuest().getCedula()
        );
    }

    public static Reservation mapperReservationToUpdate(ReservationRequestDTO reservationRequestDTO,
                                                                                    Reservation reservation,
                                                                                    BigDecimal cost) {

        if (reservationRequestDTO.getCheckIn() != null && reservationRequestDTO.getCheckOut() != null) {

            reservation.setCheckIn(reservationRequestDTO.getCheckIn());
            reservation.setCheckOut(reservationRequestDTO.getCheckOut());
            reservation.setCostOfReservation(cost);
        }

        return reservation;
    }

    public static Reservation mapperReservationToSoftDelete(Reservation reservation) {

        reservation.setStatus(Status.Inactiva);
        return  reservation;
    }
}
