package test;

import DTO.UserDTO;
import controller.LoginController;


public class TestLogin {

    public void run() {

        String username = "admin";
        String password = "admin";

        UserDTO userDTO = new UserDTO(username, password);

        LoginController loginController = new LoginController();

        if (loginController.userExists())
            loginController.registerUser(userDTO);
        else
            System.out.println("si funciona");
    }

}
