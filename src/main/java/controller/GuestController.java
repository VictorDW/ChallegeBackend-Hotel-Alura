package controller;

import DAO.GuestDAOImpl;
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

    public List<GuestDTO> getAllGuest() {
        return guestService.getAllGuest();
    }

    public Boolean updateGuest(GuestRequestDTO guestRequestDTO) {
        return guestService.updateGuest(guestRequestDTO);
    }

    public Boolean softUpdateGuest(Long guestID) {
        return guestService.softDeleteGuest(guestID);
    }

}
