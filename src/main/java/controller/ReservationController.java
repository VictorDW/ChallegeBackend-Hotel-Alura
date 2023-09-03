package controller;

import DAO.GuestDAO;
import DAO.impl.GuestDAOImpl;
import DAO.ReservationDAO;
import DTO.GuestRequestDTO;
import DAO.impl.ReservationDAOImpl;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;
import service.GuestService;
import service.ReservationService;
import service.impl.GuestServiceImpl;
import service.impl.ReservationServiceImpl;
import util.JPAUtils;

import java.util.List;

public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController() {
        ReservationDAO reservationDAO = new ReservationDAOImpl(JPAUtils.getEntityManager());
        GuestDAO guestDAO= new GuestDAOImpl(JPAUtils.getEntityManager());

        GuestService guestService = new GuestServiceImpl(guestDAO);
        reservationService = new ReservationServiceImpl(reservationDAO, guestService);

    }

    public void createReservation(ReservationRequestDTO reservationRequestDTO, GuestRequestDTO guestDTO) {
        reservationService.createReservation(reservationRequestDTO, guestDTO);
    }

    public List<ReservationDTO> getAllReservation() {
        return reservationService.getAllReservation();
    }

    public Boolean updateReservation(ReservationRequestDTO reservationRequestDTO) {
        return  reservationService.updateReservation(reservationRequestDTO);
    }

    public Boolean softDeleteReservation(Long reservationID) {
        return reservationService.softDeleteReservation(reservationID);
    }

    public List<ReservationDTO> getReservationByParameters(ReservationByParametersDTO reservationByParametersDTO) {
        return reservationService.getReservationByParameters(reservationByParametersDTO);
    }

}
