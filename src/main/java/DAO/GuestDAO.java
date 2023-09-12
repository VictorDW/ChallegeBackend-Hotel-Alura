package DAO;

import model.Guest;

public interface GuestDAO extends JpaPersistence<Guest> {

    Guest findGuestByCedula(String cedula);
}
