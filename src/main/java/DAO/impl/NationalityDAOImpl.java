package DAO.impl;

import DAO.NationalityDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import modelo.Nationality;
import modelo.Reservation;

import java.util.List;

public class NationalityDAOImpl implements NationalityDAO {

    private final EntityManager entityManager;

    public NationalityDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(Nationality nationality) {
        entityManager.getTransaction().begin();
        entityManager.merge(nationality);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Nationality> getAll() {

        String query = "SELECT N FROM Nationality N ORDER BY N.gentilicioMan ASC";
        return entityManager.createQuery(query, Nationality.class).getResultList();
    }
    @Override
    public Nationality getById(Long id) {
        try {
            return entityManager.find(Nationality.class, id);
        }catch (NoResultException e) {
            return null;
        }
    }
}
