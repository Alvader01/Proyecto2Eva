package Controller;

import Model.Repos.RepoUser;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;

import java.security.NoSuchAlgorithmException;

public class RepoUserController {
    RepoUser repoUser = RepoUser.getInstance();
    Session session = Session.getInstance();

    /**
     * Añade usuario a la lista de usuarios
     */
    public void createUser() throws NoSuchAlgorithmException {
        String name = IO.readString("Introduce el nombre: ");
        String username = IO.readString("Introduce el nombre de usuario: ");
        String password = IO.readString("Introduce la contraseña: ");
        String email = getEmailWithFormat();
        if (!userExists(username) || isEmailUnique(email)) {
            User newUser = new User(name, username, password, email);
            repoUser.create(newUser);
        } else {
            System.out.println("El usuario ya existe");
        }
    }

    public void showAllUsers() {
        for (User user : repoUser.getAll()) {
            System.out.println(user);
        }
    }

    public void showUser(User user) {
        System.out.println(user);
    }

    /**
     * Actualizar el nombre del usuario
     *
     * @param username usuario al que cambiar el nombre
     * @param newName  nuevo nombre del usuario
     */
    public User updateName(String username, String newName) {
        User userToUpdate = repoUser.getById(username);
        if (userToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setName(newName);
            repoUser.update(userToUpdate);
            MainView.showMessage("El nombre del usuario ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el usuario puede modificar su nombre");
        }
        return userToUpdate;
    }

    /**
     * Actualizar el username del usuario
     *
     * @param username    usuario al que cambiar el username
     * @param newUsername nuevo username del usuario
     */
    public User updateUsername(String username, String newUsername) {
        User userToUpdate = repoUser.getById(username);
        if (userToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setUsername(newUsername);
            repoUser.update(userToUpdate);
            MainView.showMessage("El username del usuario ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el usuario puede modificar su username");
        }
        return userToUpdate;
    }

    /**
     * Actualizar el nombre del usuario
     *
     * @param username usuario al que cambiar la contraseña
     * @param newPassword nueva contraseña del usuario
     */
    public void updatePassword(String username, String newPassword) throws NoSuchAlgorithmException {
        User userToUpdate = repoUser.getById(username);
        if (userToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setPassword(newPassword);
            repoUser.update(userToUpdate);
            MainView.showMessage("La contraseña del usuario ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el usuario puede modificar su contraseña");
        }
    }

    /**
     * Actualizar el email del usuario
     *
     * @param username usuario al que cambiar el email
     * @param newEmail nuevo email del usuario
     */
    public void updateEmail(String username, String newEmail) {
        User userToUpdate = repoUser.getById(username);
        if (userToUpdate != null &&
                session.getLoggedInUser().getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setEmail(newEmail);
            repoUser.update(userToUpdate);
            MainView.showMessage("El email del usuario ha sido modificado exitosamente.");
        } else {
            MainView.showMessage("Solo el usuario puede modificar su email");
        }
    }

    /**
     * Borrar usuario de la lista de usuarios
     *
     * @param user usuario a borrar
     */
    public void deleteUser(User user) {
        if (session.getLoggedInUser().getUsername().equals(user.getUsername())) {
            repoUser.delete(user.getUsername());
        }
    }

    /**
     * Obtener el email del usuario con el formato correcto
     *
     * @return Email del usuario
     */
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
    public boolean isValidEmailFormat(String email) {
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
    }

    /**
     * Comprobar si el usuario existe segun el username
     *
     * @param username Username del usuario
     * @return True si el usuario ya existe, false si no existe
     */
    public boolean userExists(String username) {
        boolean userExists = false;
        for (User user : repoUser.getAll()) {
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
        for (User user : repoUser.getAll()) {
            if (user.getEmail().equals(email)) {
                isUniqueUser = true;
            }
        }
        return isUniqueUser;
    }

    public User getUser(String user) {
        return repoUser.getById(user);
    }
}