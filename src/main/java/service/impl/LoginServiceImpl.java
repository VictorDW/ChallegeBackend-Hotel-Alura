package service.impl;

import DAO.LoginDAO;
import DTO.UserDTO;
import modelo.Login;
import service.LoginService;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    private final LoginDAO loginDAO;
    private boolean isFount;

    public LoginServiceImpl(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    @Override
    public boolean autenticar(UserDTO userDTO) {

        Optional<Login> login = loginDAO.findByUsername(userDTO.getUsername());

        login.ifPresentOrElse(value ->
                         isFount = value.getPassword().equals(userDTO.getPassword()),
                         ()-> isFount = false);

        return isFount;
    }
}
