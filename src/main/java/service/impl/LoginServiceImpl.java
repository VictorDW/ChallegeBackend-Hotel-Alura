package service.impl;

import DAO.LoginDAO;
import DTO.UserDTO;
import com.password4j.Hash;
import com.password4j.Password;
import model.Login;
import service.LoginService;

import java.util.Optional;

public class LoginServiceImpl implements LoginService  {
    private final LoginDAO loginDAO;
    private boolean isFount;

    /**
     * Constructor que recibe la instancia del DAO desde el controller
     * @param loginDAO
     */
    public LoginServiceImpl(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }


    @Override
    public void registerUser(UserDTO userDTO) {

        Login login = new Login();

        Hash hash = Password.hash(userDTO.getPassword()).withBcrypt();
        login.setUsername(userDTO.getUsername());
        login.setPassword(hash.getResult());

        loginDAO.registerUser(login);
    }

    /**
     * Autentica si el usuario existe y si la contraseña concuerda con la que está en la base datos
     * @param userDTO contiene los datos para autenticar
     * @return Un booleano que valida si esta autorizado o no
     */
    @Override
    public boolean autenticar(UserDTO userDTO) {

        Optional<Login> login = loginDAO.findByUsername(userDTO.getUsername());

        login.ifPresentOrElse(
                value ->{
                        String hashFromDB = value.getPassword();
                        isFount =  Password.check(userDTO.getPassword(), hashFromDB).withBcrypt();
                        },
                ()-> isFount = false);

        return isFount;
    }
}
