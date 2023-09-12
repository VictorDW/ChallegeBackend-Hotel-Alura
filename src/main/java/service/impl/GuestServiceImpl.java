package service.impl;

import DAO.GuestDAO;
import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import DTO.NationalityRequestDTO;
import model.Guest;
import model.Nationality;
import model.Status;
import service.GuestService;
import service.NationalityService;
import service.mappers.MapperGuest;

import java.util.ArrayList;
import java.util.List;

public class GuestServiceImpl implements GuestService {

    private final GuestDAO guestDAO;
    private  NationalityService nationalityService;

    public GuestServiceImpl(GuestDAO guestDAO) {
        this.guestDAO = guestDAO;
    }

    public GuestServiceImpl(GuestDAO guestDAO, NationalityService nationalityService) {
        this.guestDAO = guestDAO;
        this.nationalityService = nationalityService;
    }
    @Override
    public Nationality loadNationality(NationalityRequestDTO nationalityRequestDTO) {
       return nationalityService.getByIdNationality(nationalityRequestDTO);
    }

    @Override
    public Guest verifyGuest(GuestRequestDTO guestRequestDTO) {

        Guest guest = guestDAO.findGuestByCedula(guestRequestDTO.getCedula());

        if (guest != null) {
            guest.setStatus(Status.Activa);
            return guest;
        }

        Nationality nationality = loadNationality(guestRequestDTO.getNationality());

        return MapperGuest.mapperGuest(guestRequestDTO, nationality);
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
    public List<GuestDTO> getGuestsByCedula(String cedula) {

        Guest guest = guestDAO.findGuestByCedula(cedula);
        List<GuestDTO> guestDTOList = new ArrayList<>();

        if (guest == null) return guestDTOList;

        guestDTOList.add(
                MapperGuest.mapperGuestToGuestDTO(guest)
        );

        return guestDTOList;
    }

    @Override
    public Boolean updateGuest(GuestRequestDTO guestRequestDTO) {

        System.out.println(guestRequestDTO);
        Guest guest = guestDAO.getById(guestRequestDTO.getId());

        if (guest == null) return false;

        Nationality nationality = loadNationality(guestRequestDTO.getNationality());

        guestDAO.update(
                MapperGuest.mapperGuestToUpdate(guestRequestDTO, guest, nationality)
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

        return true;
    }
}


