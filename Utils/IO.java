package Utils;

import View.MainView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    private static Scanner teclado = new Scanner(System.in);

    /**
     * Este metodo lo que hace es mostrar un mensaje y leer una cadena.
     * @param msg el mensaje a mostrar
     * @return el mensaje introducido
     */
    public static String readString(String msg) {

        String input;
        System.out.print(msg);
        input = teclado.nextLine();
        if (input.isEmpty() ||  input.isBlank()) {
            do {
                input = teclado.nextLine();
            } while (input.isEmpty() ||  input.isBlank());
        }
        return input;
    }
    /**
     * Este metodo lo que hace es mostrar un mensaje y leer un entero.
     * @param message el mensaje a mostrar
     * @return el entero introducido
     */
    public static int readInt(String message) {
        boolean isValidInput = false;
        int value = 0;

        while (!isValidInput) {
            try {
                System.out.print(message + " ");
                value = teclado.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error, Ingresa un número válido.");
                teclado.nextLine();
            }
        }
        return value;
    }

    /**
     * Este metodo lo que hace es mostrar un mensaje y leer una fecha.
     * @param message el mensaje a mostrar
     * @return la fecha introducida
     */
    public static LocalDate readDate(String message) {
        LocalDate inputDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean fechaValida = false;

        while (!fechaValida) {
            try {
                System.out.println("Utiliza el formato dd-MM-yyyy");
                System.out.print(message);
                String dateString = teclado.nextLine();

                // Intenta convertir la cadena a LocalDate
                inputDate = LocalDate.parse(dateString, formatter);

                // Verifica si la fecha ingresada no es anterior a la fecha actual
                if (inputDate.isAfter(LocalDate.now())) {
                    fechaValida = true;
                } else {
                    System.out.println("Error: La fecha no puede ser anterior a la fecha actual.");
                }
            } catch (Exception e) {
            }
        }

        return inputDate;
    }

    /**
     * Este metodo lo que hace es mostrar un mensaje y leer una fecha final.
     * @param message el mensaje a mostrar
     * @param startDate la fecha de inicio
     * @return la fecha introducida
     */
    public static LocalDate readFinalDate(String message, LocalDate startDate) {
        LocalDate inputDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean fechaValida = false;

        while (!fechaValida) {
            try {
                System.out.print(message);
                String dateString = teclado.nextLine();

                // Intenta convertir la cadena a LocalDate
                inputDate = LocalDate.parse(dateString, formatter);

                // Verifica si la fecha ingresada no es anterior a la fecha de inicio
                if (!inputDate.isBefore(startDate)) {
                    fechaValida = true;
                } else {
                    System.out.println("Error: La fecha no puede ser anterior a la fecha de inicio.");
                }
            } catch (Exception e) {
            }
        }

        return inputDate;
    }
}
