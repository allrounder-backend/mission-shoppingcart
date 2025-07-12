package api;

import java.util.Scanner;

public class Console {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void println(String msg) {
        System.out.println(msg);
    }

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static String readLine() {
        return scanner.nextLine();
    }
}