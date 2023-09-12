package service;

import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import model.Guest;
import model.Nationality;

import java.util.List;

public interface GuestService {

    Nationality loadNationality(NationalityRequestDTO nationalityRequestDTO);
    Guest verifyGuest(GuestRequestDTO guestRequestDTO);
    List<GuestDTO> getAllGuest();
    List<GuestDTO> getGuestsByCedula(String cedula);
    Boolean updateGuest(GuestRequestDTO guestRequestDTO);
    Boolean softDeleteGuest(Long guestID);

}
