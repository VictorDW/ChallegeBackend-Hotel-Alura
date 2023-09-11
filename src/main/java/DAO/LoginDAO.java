package DAO;

import model.Login;
import java.util.Optional;

public interface LoginDAO {

    void registerUser(Login user);
    Optional<Login> findByUsername(String username);
    Optional<Login> getUser();
}
