package service;

import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import model.Guest;

import java.util.List;

public interface GuestService {

    void loadNationality( NationalityService nationalityService);
    Guest verifyGuest(GuestRequestDTO guestRequestDTO);
    List<GuestDTO> getAllGuest();
    List<GuestDTO> getGuestsByCedula(String cedula);
    Boolean updateGuest(GuestRequestDTO guestRequestDTO);
    Boolean softDeleteGuest(Long guestID);

}
