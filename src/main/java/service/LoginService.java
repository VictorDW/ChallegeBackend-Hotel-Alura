package service;

import DTO.UserDTO;

public interface LoginService  {

    void registerUser(UserDTO userDTO);
    boolean autenticar(UserDTO userDTO);
}
