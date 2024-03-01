package View;

import java.util.Scanner;

public class MainView {

    public void showMainView(){
        System.out.println("******************************************************************************");
        System.out.println(" ___                 _              _     ___          _   _   _   _       ");
        System.out.println(" | _ \\  _ _   ___    (_)  ___   __  | |_  | __|  __ _  | | (_) | | | |  ___ ");
        System.out.println(" |  _/ | '_| / _ \\   | | / -_) / _| |  _| | _|  / _` | | | | | | | | | / _ \\");
        System.out.println(" |_|   |_|   \\___/  _/ | \\___| \\__|  \\__| |_|   \\__,_| |_| |_| |_| |_| \\___/");
        System.out.println("                   |__/                                                     ");
        System.out.println("******************************************************************************");
    }

    /**
     * Menu para inicio del programa.
     */
    public void showMenu(){
        System.out.println("1. Crear usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Cerrar programa");
    }

    /**
     * Menu para registrar usuario creando un nuevo usuario.
     */
    public void showRegister(){
        System.out.println("Por favor, introduzca los siguientes datos:");
        System.out.println("Nombre: ");
        System.out.println("Usuario: ");
        System.out.println("Contraseña: ");
        System.out.println("Email: ");
    }

    /**
     * Mostrar mensaje.
     * @param message recibe el mensaje a mostrar.
     */
    public void showMessage(String message){
        System.out.println(message);
    }
}
