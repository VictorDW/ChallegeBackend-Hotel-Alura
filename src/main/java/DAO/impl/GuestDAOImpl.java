package DAO.impl;

import DAO.GuestDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.NoResultException;
import model.Guest;
import model.Status;

import java.util.List;

public class GuestDAOImpl implements GuestDAO {
    private final EntityManager entityManager;

    public GuestDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(Guest guest){
        entityManager.getTransaction().begin();
        entityManager.merge(guest);
        entityManager.getTransaction().commit();
    }

    public Guest findGuestByCedula(String cedula) {

        String query = "SELECT G FROM Guest G WHERE G.cedula=:dni";

        try {
            return entityManager.createQuery(query, Guest.class)
                    .setParameter("dni", cedula)
                    .getSingleResult();

        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Guest getById(Long id) {

        try {
            return entityManager.find(Guest.class, id);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Guest> getAll() {

        String query = "SELECT G FROM Guest G WHERE NOT (G.status =:status)";
        return entityManager.createQuery(query, Guest.class).setParameter("status", Status.Inactiva).getResultList();
    }
}
