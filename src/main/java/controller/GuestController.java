package controller;

import DAO.NationalityDAO;
import DAO.impl.GuestDAOImpl;
import DAO.impl.NationalityDAOImpl;
import DTO.GuestDTO;
import DTO.GuestRequestDTO;
import service.GuestService;
import service.NationalityService;
import service.impl.GuestServiceImpl;
import service.impl.NationalityServiceImpl;
import util.JPAUtils;

import java.util.List;

public class GuestController {

    private final GuestService guestService;

    public GuestController() {

        GuestDAOImpl guestDAO = new GuestDAOImpl(JPAUtils.getEntityManager());
        NationalityDAO nationalityDAO = new NationalityDAOImpl(JPAUtils.getEntityManager());

        NationalityService nationalityService = new NationalityServiceImpl(nationalityDAO);
        guestService = new GuestServiceImpl(guestDAO);
        guestService.loadNationality(nationalityService);
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
