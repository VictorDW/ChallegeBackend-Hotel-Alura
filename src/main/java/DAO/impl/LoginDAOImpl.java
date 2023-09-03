package DAO.impl;

import DAO.LoginDAO;
import modelo.Login;

import jakarta.persistence.EntityManager;


public class LoginDAOImpl implements LoginDAO {

    private final EntityManager entityManager;

   public LoginDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Login findByUsername(String username) {
       String query = "SELECT L from Login L WHERE L.username =:username";
      return entityManager.createQuery(query, Login.class)
                                      .setParameter("username",username)
                                      .getSingleResult();
    }
}
