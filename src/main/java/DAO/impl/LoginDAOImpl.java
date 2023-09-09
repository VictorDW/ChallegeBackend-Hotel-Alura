package DAO.impl;

import DAO.LoginDAO;
import jakarta.persistence.NoResultException;
import modelo.Login;

import jakarta.persistence.EntityManager;

import java.util.Optional;


public class LoginDAOImpl implements LoginDAO {

    private final EntityManager entityManager;

   public LoginDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
}
