package Controller;

import Model.Repos.RepoProject;
import Model.Repos.RepoUser;
import Model.Session;
import Model.User;
import Utils.IO;
import View.MainView;

import java.security.NoSuchAlgorithmException;

public class MainController {

    MainView view = new MainView();
    SubController subController = new SubController();
    RepoUserController userController = new RepoUserController();
    Session session = Session.getInstance();
    RepoProject repoProject = RepoProject.getInstance();
    RepoUser repoUser = RepoUser.getInstance();

    public void run() {
        try {
            startMainMenu();
        }catch (NoSuchAlgorithmException e){
            e.fillInStackTrace();
        }
    }

    public void startMainMenu() throws NoSuchAlgorithmException {
        int option;
        do {
            view.showMainView();
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

    public void stop(){
        repoProject.save();
        repoUser.save();
        MainView.showMessage("ADIO");
    }
}
