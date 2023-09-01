package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Reservation;
import modelo.Status;

import java.time.LocalDate;
import java.util.List;

public class ReservationDAO {

    private final EntityManager entityManager;
    public ReservationDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void update(Reservation reservation){
        entityManager.getTransaction().begin();
        entityManager.merge(reservation);
        entityManager.getTransaction().commit();
    }

    public List<Reservation> getAll() {
        String query = "SELECT R FROM Reservation R WHERE NOT (R.status =:status)";
        return entityManager.createQuery(query, Reservation.class).setParameter("status", Status.Inactiva).getResultList();
    }

    public Reservation getById(Long id) {
        try {
            return entityManager.find(Reservation.class, id);
        }catch (NoResultException e) {
            return null;
        }
    }

    public List<Reservation> getByParameters(String reservationCod, LocalDate checkIn, LocalDate checkOut) {

        StringBuilder queryBasic = new StringBuilder("SELECT R FROM Reservation R WHERE 1=1 ");

        if (reservationCod != null && !reservationCod.trim().isEmpty()) {
            queryBasic.append("AND R.reservationCod=:cod ");
        }
        if (checkIn != null && checkOut != null) {
            queryBasic.append("AND R.checkIn BETWEEN :checkin AND :checkout AND R.checkOut BETWEEN :checkin AND :checkout");
        }

        TypedQuery<Reservation> queryComplet = entityManager.createQuery(queryBasic.toString(), Reservation.class);

        if (reservationCod != null && !reservationCod.trim().isEmpty()) {
           queryComplet.setParameter("cod", reservationCod);
        }
        if (checkIn != null && checkOut != null) {
            queryComplet.setParameter("checkin", checkIn)
                                .setParameter("checkout", checkOut);
        }

        return queryComplet.getResultList();
    }
}
