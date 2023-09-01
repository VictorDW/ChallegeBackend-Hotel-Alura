package service;

import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import modelo.Guest;

import java.util.List;

public interface GuestService {
    Guest verifyGuest(GuestRequestDTO guestRequestDTO);
    List<GuestDTO> getAllGuest();
    Boolean updateGuest(GuestRequestDTO guestRequestDTO);
    Boolean softDeleteGuest(Long guestID);
}
