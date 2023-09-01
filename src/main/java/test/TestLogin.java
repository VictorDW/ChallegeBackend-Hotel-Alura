package test;

import controller.LoginController;


public class TestLogin {

    public static void main(String[] args) {

        String username = "victor";
        String password = "1223";

        LoginController loginController = new LoginController();

        if (loginController.autenticarUser(username,password)){
            System.out.println("Entro");
        }else
            System.out.println("no entro");

    }

}
