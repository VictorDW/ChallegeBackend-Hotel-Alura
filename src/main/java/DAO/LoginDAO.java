package DAO;

import DTO.UserDTO;
import model.Login;

import java.util.Optional;

public interface LoginDAO {

    void registerUser(Login user);

    Optional<Login> findByUsername(String username);
}
