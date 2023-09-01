package DAO;

import modelo.Login;

import jakarta.persistence.EntityManager;

import java.util.Optional;

public class LoginDAO {

    private final EntityManager entityManager;

   public LoginDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public Login findByUsername(String username) {
       String query = "SELECT L from Login L WHERE L.username =:username";
      return entityManager.createQuery(query, Login.class)
                                      .setParameter("username",username)
                                      .getSingleResult();
    }
}
