package SERVICES;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Get {

    public static String texto() {

        Scanner get = new Scanner(System.in);

        return get.nextLine();

    }

    public static int inteiro() {

        int valor = 0;
        Scanner get = new Scanner(System.in);

        while(true) {

            try {

                valor = get.nextInt();
                break;

            } catch (InputMismatchException e) {

                System.err.println("\n\nApenas números inteiros");
                System.out.print("Tente novamente: ");

            }

        }

        return valor;

    }

}
