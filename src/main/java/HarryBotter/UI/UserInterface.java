package HarryBotter.UI;
import java.util.Scanner;
/**
 * Handles interactions with the user.
 * Displays messages, reads user input, and prints errors.
 */
public class UserInterface {
    private static final String LINE =
            "____________________________________________________________";

    private final Scanner scanner;

    //constructor
    public UserInterface(){
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE);

        System.out.println(" _   _                           ____        _   _            ");
        System.out.println("| | | | __ _ _ __ _ __ _   _    | __ )  ___ | |_| |_ ___ _ __ ");
        System.out.println("| |_| |/ _` | '__| '__| | | |   |  _ \\ / _ \\| __| __/ _ \\ '__|");
        System.out.println("|  _  | (_| | |  | |  | |_| |   | |_) | (_) | |_| ||  __/ |   ");
        System.out.println("|_| |_|\\__,_|_|  |_|   \\__, |   |____/ \\___/ \\__|\\__\\___|_|   ");
        System.out.println("                       |___/                                   ");

        System.out.println(" Welcome to HARRYBOTTER ");
        System.out.println();
        System.out.println("A magical task manager to keep your life in order.");
        System.out.println();
        System.out.println("I can help you manage:");
        System.out.println(" • todos");
        System.out.println(" • deadlines");
        System.out.println(" • events");
        System.out.println(" • and even find tasks with magic ");
        System.out.println();
        System.out.println("Example spells you can cast:");
        System.out.println(" todo read potion book");
        System.out.println(" deadline return library book /by June 6th");
        System.out.println(" event quidditch practice /from 2pm /to 4pm");
        System.out.println(" list");
        System.out.println();
        System.out.println("Type 'bye' when you're done.");
        System.out.println("Mischief managed.");
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showExit() {
        System.out.println("Cheers! I'm off to play some quiddich!");
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(message);
        System.out.println(LINE);
    }
}
