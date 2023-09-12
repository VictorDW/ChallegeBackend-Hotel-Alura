package service;

import DTO.UserDTO;
import model.Login;

import java.util.Optional;

public interface LoginService  {

    void registerUser(UserDTO userDTO);
    boolean autenticar(UserDTO userDTO);
    boolean userExists();
}
