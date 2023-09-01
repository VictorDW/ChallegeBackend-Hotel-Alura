package DAO;

import modelo.Guest;

public interface GuestDAO extends JpaPersistence<Guest> {

    Guest findGuestByCedula(String cedula);
}
