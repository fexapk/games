package src;

import src.models.*;
import src.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    }

    public static Game createGameInput(Scanner scan) {

    }

    public static double getDouble(Scanner scan, String text) {
        double number;
        while (true) {
            System.out.print(text + " > ");
            if (!scan.hasNextDouble()) {
                scan.nextLine();
                Messages.printWarning("Should be a number");
                continue;
            }
            number = scan.nextDouble();
            break;
        }
        return number;
    }
}
