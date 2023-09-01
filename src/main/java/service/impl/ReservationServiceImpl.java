package service.impl;

import DTO.GuestRequestDTO;
import DAO.ReservationDAO;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;
import modelo.Reservation;
import service.GuestService;
import service.ReservationService;
import service.mappers.MapperReservation;

import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final GuestService guestService;
    private final ReservationDAO reservationDAO;

    public ReservationServiceImpl(ReservationDAO reservationDAO, GuestService guestService) {
        this.reservationDAO = reservationDAO;
        this.guestService = guestService;
    }

    @Override
    public void createReservation(ReservationRequestDTO reservationRequestDTO, GuestRequestDTO guestRequestDTO) {

        reservationDAO.update(
                MapperReservation.mapperReservationRequestDTOToReservation(
                        reservationRequestDTO,
                        guestService.verifyGuest(guestRequestDTO))
        );
    }

    @Override
    public List<ReservationDTO> getAllReservation() {

        List<ReservationDTO> reservationDTOList = new ArrayList<>();

        reservationDAO.getAll().forEach((reservation ->
                reservationDTOList.add(
                        MapperReservation.mapperReservationToReservationDTO(reservation)
                )
        ));

        return reservationDTOList;
    }

    @Override
    public Boolean updateReservation(ReservationRequestDTO reservationRequestDTO) {

        Reservation reservation = reservationDAO.getById(reservationRequestDTO.getId());

        if (reservation == null) return false;

        Period period = Period.between(reservationRequestDTO.getCheckIn(),
                reservationRequestDTO.getCheckOut());

        BigDecimal cost = new BigDecimal(period.getDays() * 43300);

        reservationDAO.update(
                MapperReservation.mapperReservationToUpdate(
                        reservationRequestDTO,
                        reservation,
                        cost)
        );

        return true;
    }

    @Override
    public Boolean softDeleteReservation(Long reservationID) {

        Reservation reservation = reservationDAO.getById(reservationID);

        if (reservation == null) return false;

        reservationDAO.update(
                MapperReservation.mapperReservationToSoftDelete(reservation)
        );

        return true;
    }

    @Override
    public List<ReservationDTO> getReservationByParameters(ReservationByParametersDTO reservationByParametersDTO) {

        List<Reservation> reservationList =
                reservationDAO.getByParameters(
                        reservationByParametersDTO.getReservationCod(),
                        reservationByParametersDTO.getCheckIn(),
                        reservationByParametersDTO.getCheckOut()
                );

        List<ReservationDTO> reservationDTOList = new ArrayList<>();


        reservationList.forEach((reservation ->
                reservationDTOList.add(
                        MapperReservation.mapperReservationToReservationDTO(reservation)
                )
        ));


        return reservationDTOList;
    }
}
