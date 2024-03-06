package Utils;

import java.util.Scanner;

public class IO {
    private static Scanner teclado = new Scanner(System.in);

    /**
     * Este metodo lo que hace es mostrar un mensaje y leer una cadena.
     * @param message el mensaje a mostrar
     * @return el mensaje introducido
     */
    public String readString(String message) {
        System.out.println(message);
        String input = " ";
        try {
            input = teclado.nextLine();
        } catch (Exception e) {
            System.out.println("Error al leer la cadena.");
            // Limpia el buffer del scanner para evitar un bucle infinito
            teclado.nextLine();
        }
        return input;
    }

    /**
     * Este metodo lo que hace es mostrar un mensaje y leer un entero.
     * @param message el mensaje a mostrar
     * @return el entero introducido
     */
    public int readInt(String message) {
        System.out.println(message);
        int opcion = 0;
        try {
            opcion = teclado.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Ingresa un valor entero válido.");
            // Limpia el buffer del scanner para evitar un bucle infinito
            teclado.nextLine();
        }
        return opcion;
    }
}