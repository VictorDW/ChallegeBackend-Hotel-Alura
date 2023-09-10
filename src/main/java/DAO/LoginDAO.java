package DAO;

import model.Login;

import java.util.Optional;

public interface LoginDAO {
    Optional<Login> findByUsername(String username);
}
