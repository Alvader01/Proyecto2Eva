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
     * @param password La contrasena del usuario
     * @return El usuario logueado
     */
    @Override
    public User login(String username, String password) throws NoSuchAlgorithmException {
        User foundUser = null;
        for (User user : users) {
            if (user.getName().equals(username) && user.isMyPassword(password)) {
                foundUser = user;
            }
        }
        loggedInUser = foundUser;
        return foundUser;
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
