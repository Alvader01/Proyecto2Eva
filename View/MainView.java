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
    public static void showMenu(){
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Crear usuario");
        System.out.println("3. Cerrar programa");
    }

    /**
     * Menu para cambiar el estado de la tarea.
     */
    public static void changeStatus(){
        System.out.println("1. Sin iniciar\n2. En progreso\n3. finalizado\n");
    }

    /**
     * Mostrar mensaje.
     * @param message recibe el mensaje a mostrar.
     */
    public static void showMessage(String message){
        System.out.println(message);
    }
}
