import java.util.Scanner;

public class HarryBotter {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Harry, your loyal chatbot mate!");
        System.out.println("Oi! What can I do for you today? Type 'bye' when you're done, gov'nor.");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true){
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if(input.equals("bye")){
                System.out.println("Cheerio! I'm off to play some quiddich! Hope to see you lad again soon alright, mate!");
                System.out.println("____________________________________________________________");
                break;
            }
            else{
                System.out.println("Ah, you said: \"" + input + "\", righty-o!");
                System.out.println("____________________________________________________________");
            }

        }
    }
}
