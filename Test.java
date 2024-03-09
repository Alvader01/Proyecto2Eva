import Controller.RepoUserController;
import Model.Repos.RepoUser;
import Model.User;
import Utils.IO;

import java.security.NoSuchAlgorithmException;

public class Test {
    RepoUser repoUser = RepoUser.getInstance();
    public static void main(String[] args) throws NoSuchAlgorithmException {
        RepoUserController repoUserController = new RepoUserController();
        repoUserController.createUser();
        repoUserController.showAllUsers();

    }

    public void createUser() throws NoSuchAlgorithmException {
        String name = IO.readString("Introduce el nombre: ");
        String username = IO.readString("Introduce el nombre de usuario: ");
        String password = IO.readString("Introduce la contraseña: ");
        String email = getEmailWithFormat();
        User newUser = new User(name, username, password, email);
        repoUser.create(newUser);
    }

    public static String getEmailWithFormat() {
        String email;
        do {
            email = IO.readString("Introduce el email con formato (emailejemplo@hola.mundo): ");
        } while (!isValidEmailFormat(email));
        return email;
    }

    /**
     * Comprobar si el email tiene el formato correcto
     *
     * @param email Email del usuario
     * @return True si el email tiene el formato correcto, false si no tiene el formato correcto
     */
    public static boolean isValidEmailFormat(String email) {
        return email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
    }
}
