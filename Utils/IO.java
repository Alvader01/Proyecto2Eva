package Utils;

import java.util.Scanner;

public class IO {
    private static Scanner teclado = new Scanner(System.in);
    public String readString(String message){
        System.out.println(message);
        return teclado.nextLine();
    }

    public int readInt(String message){
        System.out.println(message);
        int opcion = 0;
        return teclado.nextInt();
    }
}
