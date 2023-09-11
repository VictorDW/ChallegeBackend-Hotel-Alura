package test;

import DTO.UserDTO;
import controller.LoginController;


public class TestLogin {

    public static void main(String[] args) {

        String username = "admin";
        String password = "admin";

        UserDTO userDTO = new UserDTO(username, password);

        LoginController loginController = new LoginController();

        //loginController.registerUser(userDTO);
    }

}
