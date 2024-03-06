package Model.Repos;

import Interfaces.Repos.IRepoUser;
import Model.Session;
import Model.User;
import Utils.IO;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RepoUser extends Repository<User, String> implements IRepoUser {
    private final static String FILENAME = "users.bin";
    private static RepoUser _instance;
    private Set<User> users;

    private RepoUser() {
        this.users = new HashSet<>();
    }

    public static RepoUser getInstance() {
        if (_instance == null) {
            _instance = (RepoUser) load(FILENAME);
            if (_instance == null) {
                _instance = new RepoUser();
            }
        }
        return _instance;
    }

    /**
     * Añade usuario a la lista de usuarios
     *
     * @param newUser Usuario a añadir
     */
    @Override
    public User create(User newUser) throws NoSuchAlgorithmException {
        String name = IO.readString("Introduce el nombre: ");
        String username = IO.readString("Introduce el nombre de usuario: ");
        String password = IO.readString("Introduce la contraseña: ");
        String email = getEmailWithFormat();
        if (!userExists(username) || isEmailUnique(email)) {
            newUser = new User(name, username, password, email);
            users.add(newUser);
        } else {
            System.out.println("El usuario ya existe");
        }
        return  newUser;
    }

    /**
     * Buscar usuario por nombre de usuario
     *
     * @param username Username de usuario
     * @return Usuario encontrado
     */
    @Override
    public User getById(String username) {
        User foundUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                foundUser = user;
            }
        }
        return foundUser;
    }

    /**
     * Actualiza un usuario con los datos introducidos.
     *
     * @param username el nombre de usuario a actualizar
     * @return true si se ha actualizado el usuario, false si no
     */
    @Override
    public boolean update(String username) {
        boolean userUpdated = false;
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getUsername().equals(Session.getInstance().getLoggedInUser().getUsername())) {
                user.setUsername(username);
                userUpdated = true;
            }
        }
        return userUpdated;
    }

    /**
     * Obtener la lista de usuarios
     *
     * @return Lista de usuarios
     */
    @Override
    public Collection<User> getAll() {
        return users;
    }

    /**
     * Borrar usuario de la lista de usuarios
     *
     * @param username Nombre del usuario a borrar
     * @return True si se ha eliminado el usuario, false si ha habido un error al borrar el usuario
     */
    @Override
    public boolean delete(String username) {
        boolean userDeleted = false;
        for (User user : users) {
            if (user.getUsername().equals(username) ||
                    user.getUsername().equals(Session.getInstance().getLoggedInUser().getUsername())) {
                users.remove(user);
                userDeleted = true;
            } else {
                System.out.println("El usuario no existe");
            }
        }
        return userDeleted;
    }

    /**
     * Obtener el email del usuario con el formato correcto
     *
     * @return Email del usuario
     */
    @Override
    public String getEmailWithFormat() {
        String email;
        do {
            email = IO.readString("Introduce el email con formato (emailejemplo@hola.mundo): ");
        } while (!isValidEmailFormat(email) && isEmailUnique(email));
        return email;
    }

    /**
     * Comprobar si el email tiene el formato correcto
     *
     * @param email Email del usuario
     * @return True si el email tiene el formato correcto, false si no tiene el formato correcto
     */
    @Override
    public boolean isValidEmailFormat(String email) {
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
    }

    /**
     * Comprobar si el usuario existe segun el username
     *
     * @param username Username del usuario
     * @return True si el usuario ya existe, false si no existe
     */
    @Override
    public boolean userExists(String username) {
        boolean userExists = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userExists = true;
            }
        }
        return userExists;
    }

    /**
     * comprobar si el usuario existe segun el email
     *
     * @param email Email del usuario
     * @return True si el email esta en uso, false si no esta en uso
     */
    @Override
    public boolean isEmailUnique(String email) {
        boolean isUniqueUser = false;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                isUniqueUser = true;
            }
        }
        return isUniqueUser;
    }

    /**
     * Guardar la lista de usuarios en un fichero
     *
     * @return true si se ha guardado con exito, false si no
     */
    @Override
    public boolean save() {
        return save(FILENAME);
    }
}