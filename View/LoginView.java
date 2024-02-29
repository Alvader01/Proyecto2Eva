package View;

import Interfaces.View.ILogin;

import java.util.Scanner;

public class LoginView implements ILogin {

    @Override
    public void showUser() {
        System.out.println("*************************************");
        System.out.println("Por favor introduzca su usuario y contraseña, para acceder");
        System.out.println("                                     ");
        System.out.println("Usuario: ");
    }

    @Override
    public void showPassword() {
        System.out.println("                                     ");
        System.out.println("Contraseña: ");
        System.out.println("*************************************");
    }
}
