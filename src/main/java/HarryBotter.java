import java.util.Scanner;

/**
 * HarryBotter is a simple chatbot
 * Features a wannabe British personality.
 */

public class HarryBotter {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Harry, your loyal chatbot mate!");
        System.out.println("Oi! What can I do for you today? Type 'bye' when you're done, gov'nor.");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        TaskManager manager = new TaskManager();

        while(true){
            input = scanner.nextLine().toLowerCase();
            //list tasks
            if(input.equals("list")){
                manager.listTasks();
            }
            //Exit Program
            else if(input.equals("bye")){
                System.out.println("Cheers! I'm off to play some quiddich! Hope to see you lad again soon alright, mate!");
                System.out.println("____________________________________________________________");
                break;
            }
            //Mark task
            else if(input.startsWith("mark")){
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    manager.markTask(taskIndex-1);
                } catch(Exception e){
                    System.out.println("Please provide a valid task number to mark.");
                }
            }
            //Unmark tasks
            else if(input.startsWith("unmark")){
                try {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]);
                    manager.unmarkTask(taskIndex-1);
                } catch(Exception e){
                    System.out.println("Please provide a valid task number to mark.");
                }
            }
            //Add and store new Task
            else{
                manager.addTask(input);
                System.out.println("Ah, you said: \"" + input + "\", added yo!");
                System.out.println("____________________________________________________________");
            }
        }
    }
}
