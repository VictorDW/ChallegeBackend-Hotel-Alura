package service.mappers;

import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import model.Guest;
import model.Nationality;
import model.Status;

public final class MapperGuest {

    public static Guest mapperGuest(GuestRequestDTO guestRequestDTO, Nationality nationality) {

        Guest newGuest = new Guest();
        newGuest.setCedula(guestRequestDTO.getCedula());
        newGuest.setFirstName(guestRequestDTO.getFirsName());
        newGuest.setLastName(guestRequestDTO.getLastName());
        newGuest.setDateOfBirth(guestRequestDTO.getDateOfBirth());
        newGuest.setPhone(guestRequestDTO.getPhone());
        newGuest.setNationality(nationality);
        newGuest.setStatus(Status.Activa);

        return newGuest;
    }

    public  static GuestDTO mapperGuestToGuestDTO(Guest guest) {

        return new GuestDTO(guest.getId(),
                                        guest.getCedula(),
                                        guest.getFirstName(),
                                        guest.getLastName(),
                                        guest.getDateOfBirth(),
                                        guest.getPhone(),
                                        new NationalityRequestDTO(
                                                guest.getNationality().getId(),
                                                guest.getNationality().getGentilicioMan(),
                                                guest.getNationality().getGentilicioWoman()
                                        ),
                                        guest.getStatus().toString()
        );
    }

    public static Guest mapperGuestToUpdate(GuestRequestDTO guestRequestDTO, Guest guest, Nationality nationality) {

        if(guestRequestDTO.getCedula() != null) guest.setCedula(guestRequestDTO.getCedula());
        if(guestRequestDTO.getDateOfBirth() != null) guest.setDateOfBirth(guestRequestDTO.getDateOfBirth());
        if(guestRequestDTO.getFirsName() != null) guest.setFirstName(guestRequestDTO.getFirsName());
        if(guestRequestDTO.getLastName() != null) guest.setLastName(guestRequestDTO.getLastName());
        if(guestRequestDTO.getPhone() != null) guest.setPhone(guestRequestDTO.getPhone());
        if(nationality.getId() != null) guest.setNationality(nationality);

        return guest;
    }

    public static Guest mapperGuestToSoftDelete(Guest guest) {

        guest.setStatus(Status.Inactiva);
        return  guest;
    }
}
