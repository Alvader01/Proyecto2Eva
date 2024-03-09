package Model;

import Interfaces.Model.ISession;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class Session implements ISession {
    private static Session _instance;
    private User loggedInUser;
    private Set<User> users;


    private Session() {
        this.users = new HashSet<>();
    }

    public static Session getInstance() {
        if (_instance == null) {
            _instance = new Session();
        }
        return _instance;
    }

    /**
     * Iniciar sesion de usuario
     *
     * @param username El username del usuario
     * @param password La contraseña del usuario
     * @return El usuario logueado
     */
    @Override
    public boolean login(String username, String password) throws NoSuchAlgorithmException {
        boolean foundUser = false;
        for (User user : users) {
            if (user.getName().equals(username) && user.isMyPassword(password)) {
                loggedInUser = user;
                foundUser = true;
                break;
            }
        }
        return foundUser;
    }

    /**
     * Cerrar sesion
     */
    public void logout() {
        loggedInUser = null;
    }

    /**
     * Obtener el usuario logueado
     *
     * @return El usuario logueado
     */
    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
