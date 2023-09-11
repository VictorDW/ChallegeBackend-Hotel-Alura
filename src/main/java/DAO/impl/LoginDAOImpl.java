package DAO.impl;

import DAO.LoginDAO;
import jakarta.persistence.NoResultException;
import model.Login;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


public class LoginDAOImpl implements LoginDAO {

    private final EntityManager entityManager;

   public LoginDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void registerUser(Login user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Login> findByUsername(String username) {

       try {
           String query = "SELECT L from Login L WHERE L.username =:username";

           return Optional.of(entityManager.createQuery(query, Login.class)
                   .setParameter("username", username)
                   .getSingleResult());

       }catch (NoResultException e) {
           return Optional.empty();
       }
    }

    @Override
    public Optional<Login> getUser() {

        try {
           String query = "SELECT L FROM Login L";

           return Optional.of(entityManager.createQuery(query, Login.class)
                   .getSingleResult());

        }catch (NoResultException e) {
            return Optional.empty();
        }
    }


}
