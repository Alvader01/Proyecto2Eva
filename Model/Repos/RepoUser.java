package Model.Repos;

import Model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RepoUser extends Repository<User, String> {
    private final static String FILENAME = "users.txt";
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
     * Crear un usuario y añadirlo a la lista de usuarios con correccion de errores
     *
     * @param data Data del usuario
     */
    @Override
    public User create(User data) {
        User user = null;
        if (users.add(data)) {
            user = data;
        }
        return user;
    }

    @Override
    public User getById(String id) {
        return null;
    }

    /**
     * Actualiza un usuario con los datos introducidos.
     *
     * @param data el usuario que contiene los datos
     * @return el usuario con los datos actualizados
     */
    @Override
    public User update(User data) {
        User result = getById(data.getUsername());
        if (result != null) {
            users.remove(result);
            users.add(data);
            result = data;
        }
        return result;
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
            if (user.getUsername().equals(username)) {
                users.remove(user);
                userDeleted = true;
            }
        }
        return userDeleted;
    }

    /**
     * comprobar si el usuario existe segun el username
     *
     * @param username Username del usuario
     * @return True si el usuario ya existe, false si no existe
     */
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
    public boolean save() {
        return save(FILENAME);
    }
}