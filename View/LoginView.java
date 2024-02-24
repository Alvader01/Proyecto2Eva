package View;

import Interfaces.View.ILogin;

import java.util.Scanner;

public class LoginView implements ILogin {

    private static Scanner teclado = new Scanner(System.in);
    @Override
    public String showUser() {
        String user = " ";
        System.out.println("*************************************");
        System.out.println("Por favor introduzca su usuario y contraseña, para acceder");
        System.out.println("                                     ");
        System.out.println("Usuario: ");
        return teclado.nextLine();
    }

    @Override
    public String showPassword() {
        String password = " ";
        System.out.println("                                     ");
        System.out.println("Contraseña: ");
        System.out.println("*************************************");
        return teclado.nextLine();
    }
}
