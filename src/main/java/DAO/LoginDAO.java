package DAO;

import modelo.Login;

public interface LoginDAO {
    Login findByUsername(String username);
}
