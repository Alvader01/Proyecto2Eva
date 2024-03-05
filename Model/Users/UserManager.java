package Model.Users;

import Model.Users.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    /**
     * Crear un usuario y añadirlo a la lista de usuarios
     *
     * @param name     Nombre del usuario
     * @param username Username del usuario
     * @param password Contraseña del usuario
     * @param email    Email del usuario
     */
    public void createUser(String name, String username, String password, String email) {
        if (!userExists(username) || !isEmailUnique(email)) {
            User newUser = new User(name, username, password, email);
            users.add(newUser);
        } else {
            System.out.println("El usuario ya existe");
        }
    }

    /**
     * Borrar usuario de la lista de usuarios
     *
     * @param user Usuario a borrar
     * @return True si se ha eliminado el usuario, false si ha habido un error al borrar el usuario
     */
    public boolean deleteUser(User user) {
        boolean deletedUser = false;
        if (user != null) {
            users.remove(user);
            deletedUser = true;
        }
        return deletedUser;
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
     * Metodo para encriptar la contraseña de un usuario
     *
     * @param password Contraseña del usuario
     * @return Contraseña encriptada
     */
    private String hashPassword(String password) {
        String hashedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    /**
     * Guardar la lista de usuarios en un fichero, con la contraseña encriptada
     */
    public void saveUsersToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (User user : users) {
                writer.write(user.getUsername() + " : " + hashPassword(user.getPassword()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
