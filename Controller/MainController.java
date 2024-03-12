package Controller;

import Model.Repos.RepoProject;
import Model.Repos.RepoUser;
import Model.Session;
import Utils.IO;
import View.MainView;

import java.security.NoSuchAlgorithmException;

public class MainController{
    MainView view = new MainView();
    SubController subController = new SubController();
    RepoUserController userController = new RepoUserController();
    Session session = Session.getInstance();
    RepoProject repoProject = RepoProject.getInstance();
    RepoUser repoUser = RepoUser.getInstance();

    /**
     * Inicia el menu
     * @throws NoSuchAlgorithmException
     */
    public void startMainMenu() throws NoSuchAlgorithmException {
        repoUser.load();
        repoProject.load();
        view.showMainView();
        int option;
        do {

            view.showMenu();
            option = IO.readInt("Elige una opción: ");
            switch (option) {
                case 1:
                    String username = IO.readString("Usuario: ");
                    String password = IO.readString("Contraseña: ");
                    if (session.login(username, password)) {
                        subController.menuOption1();
                    } else {
                        MainView.showMessage("Usuario o contraseña incorrecta");
                    }
                    break;
                case 2:
                    userController.createUser();
                    break;
                case 3:
                    stop();
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (option != 3);
    }

    /**
     * Para el programa y guarda.
     */
    public void stop(){
        repoProject.save();
        repoUser.save();
        MainView.showMessage("ADIO");
    }
}
