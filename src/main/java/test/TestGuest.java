package test;

import DTO.GuestRequestDTO;
import controller.GuestController;

import java.time.LocalDate;

public class TestGuest {

    public static void main(String[] args) {

        //prueba para el Update
        GuestRequestDTO guestRequestDTO = new GuestRequestDTO(
                6L,
                "116268898",
                "Victor",
                "Arenas",
                LocalDate.of(1995,9,19),
                "3156360926"
        );

        GuestController guestController = new GuestController();


       // System.out.println(guestController.updateGuest(guestRequestDTO));
       // System.out.println(guestController.softUpdateGuest(6L));
        guestController.getAllGuest().forEach(System.out::println);

    }

}
