package service;

import DTO.GuestRequestDTO;
import DTO.ReservationByParametersDTO;
import DTO.ReservationDTO;
import DTO.ReservationRequestDTO;


import java.util.List;

public interface ReservationService {

    void createReservation(ReservationRequestDTO reservationRequestDTO, GuestRequestDTO guestDTO);
    List<ReservationDTO> getAllReservation();
    Boolean updateReservation(ReservationRequestDTO reservationRequestDTO);
    Boolean softDeleteReservation(Long reservationID);
    List<ReservationDTO> getReservationByParameters(ReservationByParametersDTO reservationByParametersDTO);
}
