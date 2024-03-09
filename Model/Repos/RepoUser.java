package Model.Repos;

import Interfaces.Repos.IRepository;
import Model.User;

import java.io.Serializable;
import java.util.*;

public class RepoUser extends Repository<User, String> implements IRepository<User, String>, Serializable  {
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
    public User create(User newUser) {
        users.add(newUser);
        return newUser;
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
     * @param data datos del usuario a actualizar
     * @return el usuario actualizado
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
     * Guardar la lista de usuarios en un fichero
     *
     * @return true si se ha guardado con éxito, false si no
     */
    public boolean save() {
        return save(FILENAME);
    }

    public Repository load(){
        return load(FILENAME);
    }
}