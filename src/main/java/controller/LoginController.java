package controller;

import DAO.LoginDAO;
import DTO.UserDTO;
import jakarta.persistence.EntityManager;
import service.LoginService;
import service.impl.LoginServiceImpl;
import util.JPAUtils;

public class LoginController {

    private LoginDAO loginDAO;
    private LoginService loginService;

    public LoginController() {
        loginDAO = new LoginDAO(JPAUtils.getEntityManager());
        loginService = new LoginServiceImpl(loginDAO);
    }

    public boolean autenticarUser(String username, String password) {

        UserDTO userDTO = new UserDTO(username, password);
        return loginService.autenticar(userDTO);
    }
}
