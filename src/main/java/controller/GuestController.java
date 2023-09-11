package controller;

import DAO.impl.GuestDAOImpl;
import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import service.GuestService;
import service.impl.GuestServiceImpl;
import util.JPAUtils;

import java.util.List;

public class GuestController {

    private final GuestService guestService;

    public GuestController() {

        GuestDAOImpl guestDAO = new GuestDAOImpl(JPAUtils.getEntityManager());

        guestService = new GuestServiceImpl(guestDAO);
    }

    public GuestController(NationalityController nationalityController) {

        GuestDAOImpl guestDAO = new GuestDAOImpl(JPAUtils.getEntityManager());

        guestService = new GuestServiceImpl(guestDAO, nationalityController.getNationalityService());
    }


    public GuestService getGuestService() {
        return guestService;
    }

    public List<GuestDTO> getAllGuest() {
        return guestService.getAllGuest();
    }

    public  List<GuestDTO> getGuestsByCedula(String cedula) {
        return guestService.getGuestsByCedula(cedula);
    }

    public Boolean updateGuest(GuestRequestDTO guestRequestDTO) {
        return guestService.updateGuest(guestRequestDTO);
    }

    public Boolean softDeleteGuest(Long guestID) {
        return guestService.softDeleteGuest(guestID);
    }

}
