package controller;

import DAO.LoginDAO;
import DAO.impl.LoginDAOImpl;
import DTO.UserDTO;
import service.LoginService;
import service.impl.LoginServiceImpl;
import util.JPAUtils;

public class LoginController {

    private final LoginService loginService;

    public LoginController() {

        LoginDAO loginDAO = new LoginDAOImpl(JPAUtils.getEntityManager());
        loginService = new LoginServiceImpl(loginDAO);
    }

    public boolean autenticarUser(String username, String password) {

        UserDTO userDTO = new UserDTO(username, password);
        return loginService.autenticar(userDTO);
    }
}
