package test;

import DTO.ReservationByParametersDTO;
import DTO.ReservationRequestDTO;
import controller.ReservationController;

import java.time.LocalDate;

public class TestReservation {
    public static void main(String[] args) {


        /*
        ReservationRequestDTO reservationDTO =
                DataReservationTemporary.dataReserve(LocalDate.of(2023,8,25),
                                                                            LocalDate.of(2023,8,31),
                                                                            "Tarjeta credito");

         */



       /* ReservationRequestDTO reservationDTO =
                new ReservationRequestDTO(4L,
                                                            LocalDate.of(2023,8,18),
                                                            LocalDate.of(2023,8,30));

           System.out.println(reservationDTO);
        */

        ReservationByParametersDTO reservationByParametersDTO = new ReservationByParametersDTO();
        reservationByParametersDTO.setReservationCod("2MD3E5");
        reservationByParametersDTO.setCheckIn(LocalDate.of(2023,8,18));
        reservationByParametersDTO.setCheckOut(LocalDate.of(2023,8,30));





      /*  Nationality nationality = new Nationality();
        nationality.setId(1L);
        nationality.setGentilicioMan("Colombiano");
        nationality.setGentilicioWoman("Colombiana");

        GuestRequestDTO guestRequestDTO = new GuestRequestDTO("1121920670", "Juliana", "Solano",
                LocalDate.of(1994,12,6), "3153632599", nationality);

       */

        ReservationController reservationController = new ReservationController();
        //System.out.println(reservationController.updateReservation(reservationDTO));
        // System.out.println(reservationController.softDeleteReservation(5L));
       // reservationController.createReservation(reservationDTO, guestRequestDTO);

       // reservationController.getAllReservation().forEach(System.out::println);
       reservationController.getReservationByParameters(reservationByParametersDTO).forEach(System.out::println);

    }
}
