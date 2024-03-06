package Utils;

import java.util.Scanner;

public class IO {
    private static Scanner teclado = new Scanner(System.in);
    public static String readString(String message){
        System.out.println(message);
        return teclado.nextLine();
    }

    public static int readInt(String message){
        System.out.println(message);
        int opcion = 0;
        return teclado.nextInt();
    }
}
