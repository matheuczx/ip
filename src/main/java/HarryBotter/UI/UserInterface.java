package HarryBotter.UI;
import java.util.Scanner;

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
        System.out.println("Hello! I'm Harry, your loyal chatbot mate!");
        System.out.println("Oi! What can I do for you today?");
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
