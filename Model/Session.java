package Model;

import Interfaces.Model.ISession;
import Model.Repos.RepoUser;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class Session implements ISession {
    private static Session _instance;
    private User loggedInUser;
    RepoUser repoUser = RepoUser.getInstance();
    private Set<User> users;

    private Session() {
        this.users = (Set<User>) repoUser.getAll();
    }

    public static Session getInstance() {
        if (_instance == null) {
            _instance = new Session();
        }
        return _instance;
    }

    /**
     * Iniciar sesión con un usuario
     *
     * @param username El nombre de usuario
     * @param password La contraseña del usuario
     * @return true si el usuario existe y la contraseña coincide
     */
    @Override
    public boolean login(String username, String password) throws NoSuchAlgorithmException {
        boolean foundUser = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.isMyPassword(password)) {
                loggedInUser = user;
                foundUser = true;
                break;
            }
        }
        return foundUser;
    }

    /**
     * Cerrar sesión
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
