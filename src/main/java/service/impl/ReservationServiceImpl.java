package service.impl;

import DAO.ReservationDAO;
import DTO.GuestRequestDTO;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;
import model.Reservation;
import service.GuestService;
import service.ReservationService;
import service.mappers.MapperReservation;
import service.util.DataReservationTemporary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private  GuestService guestService;
    private final ReservationDAO reservationDAO;

    public ReservationServiceImpl(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

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

        BigDecimal cost = DataReservationTemporary.calculateReservationCost();

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
