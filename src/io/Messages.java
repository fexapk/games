package src.io;
public class Messages {

    public static void printSuccess(String text) {
        System.out.println(GREEN + text + RESET);
    }

    public static void printError(String text) {
        System.out.println(RED + text + RESET);
    }

    public static void printWarning(String text) {
        System.out.println(YELLOW + text + RESET);
    }

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
}