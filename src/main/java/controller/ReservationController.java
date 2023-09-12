package controller;

import DAO.ReservationDAO;
import DTO.GuestRequestDTO;
import DAO.impl.ReservationDAOImpl;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;
import service.ReservationService;
import service.impl.ReservationServiceImpl;
import util.JPAUtils;

import java.util.List;

public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController() {

        ReservationDAO reservationDAO = new ReservationDAOImpl(JPAUtils.getEntityManager());

        reservationService = new ReservationServiceImpl(reservationDAO);
    }

    public ReservationController(GuestController guestController) {

        ReservationDAO reservationDAO = new ReservationDAOImpl(JPAUtils.getEntityManager());

        reservationService = new ReservationServiceImpl(reservationDAO, guestController.getGuestService());
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
