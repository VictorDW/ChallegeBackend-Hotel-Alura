package service.impl;

import DAO.GuestDAO;
import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import modelo.Guest;
import modelo.Status;
import service.GuestService;
import service.mappers.MapperGuest;

import java.util.ArrayList;
import java.util.List;

public class GuestServiceImpl implements GuestService {

    private  final GuestDAO guestDAO;
    public GuestServiceImpl(GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    @Override
    public Guest verifyGuest(GuestRequestDTO guestRequestDTO) {

        Guest guest = guestDAO.findGuestByCedula(guestRequestDTO.getCedula());

        if(guest != null) {
            guest.setStatus(Status.Activa);
            return guest;
        }

        return MapperGuest.mapperGuest(guestRequestDTO);
    }

    @Override
    public List<GuestDTO> getAllGuest() {

        List<GuestDTO> guestDTOList = new ArrayList<>();

        guestDAO.getAll().forEach(guest ->
                 guestDTOList.add(
                         MapperGuest.mapperGuestToGuestDTO(guest)
                 )
        );

        return guestDTOList;
    }

    @Override
    public Boolean updateGuest(GuestRequestDTO guestRequestDTO) {

        Guest guest = guestDAO.getById(guestRequestDTO.getId());

        if (guest == null) return false;

        guestDAO.update(
                MapperGuest.mapperGuestToUpdate(guestRequestDTO, guest)
        );

        return true;
    }

    @Override
    public Boolean softDeleteGuest(Long guestID) {

        Guest guest = guestDAO.getById(guestID);

        if (guest == null) return false;

        guestDAO.update(
                MapperGuest.mapperGuestToSoftDelete(guest)
        );

        return  true;
    }
}
