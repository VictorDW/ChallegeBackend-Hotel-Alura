package service.impl;

import DAO.LoginDAO;
import DTO.UserDTO;
import modelo.Login;
import service.LoginService;

public class LoginServiceImpl implements LoginService {
    private final LoginDAO loginDAO;

    public LoginServiceImpl(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    @Override
    public boolean autenticar(UserDTO userDTO) {

        Login login = loginDAO.findByUsername(userDTO.getUsername());

        if (login == null)
            return false;
        else
            return login.getPassword().equals(userDTO.getPassword());
    }
}
