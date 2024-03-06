package View;

import Interfaces.View.IMainView;

import java.util.Scanner;

public class MainView implements IMainView {

    /**
     * Este metodo muestra el print de ProjectFalillo, que va en el menu principal.
     */
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
    public static void showMessage(String message){
        System.out.println(message);
    }

    public void createProject(){
        System.out.println("Por favor, introduzca los siguientes datos:");
        System.out.println("Nombre: ");
        System.out.println("Descripción: ");
        System.out.println("Creador: ");
    }

    public void createTask(){
        System.out.println("Por favor, introduzca los siguientes datos:");
        System.out.println("Tarea: ");
        System.out.println("Descripción: ");
        System.out.println("Fecha de inicio: ");
        System.out.println("Fecha de fin: ");
        System.out.println("Estado: ");
    }
}
