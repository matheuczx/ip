package HarryBotter;

import HarryBotter.UI.UserInterface;

import java.io.IOException;
/**
 * Handles parsing and execution of user commands.
 * Delegates actions to TaskManager and communicates errors via UserInterface.
 */
public class CommandHandler {
    private TaskManager manager;
    private UserInterface userInterface;

    /**
     * Constructs a CommandHandler with a TaskManager and UserInterface.
     *
     * The TaskManager responsible for task operations.
     * The UserInterface for displaying messages to the user.
     */
    public CommandHandler(TaskManager manager, UserInterface userInterface){
        this.manager =  manager;
        this.userInterface = userInterface;
    }

    /**
     * Parses a user's input command and executes the corresponding action.
     *
     * Supports commands:
     * - list
     * - bye
     * - mark <taskIndex>
     * - unmark <taskIndex>
     * - delete <taskIndex>
     * - find <keyword>
     * - adding new tasks (todo, deadline, event)
     *
     * @param input The user's input command.
     * @return true if the user wants to exit (bye), false otherwise.
     */
    public boolean handleInput(String input){
        input = input.toLowerCase();

        try{
            if(input.equals("list")){
                manager.listTasks();
            }
            else if(input.equals("bye")){
                return true;
            }
            else if(input.startsWith("mark")){
                handleMark(input);
            }
            else if(input.startsWith("unmark")){
                handleUnmark(input);
            }
            else if(input.startsWith("delete")){
                handleDelete(input);
            }
            else if (input.startsWith("find ")) {
                handleFind(input);
            }
            else{
                manager.addTask(input);
            }
        } catch (HarryBotterException e){
            userInterface.showError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private void handleUnmark(String input){
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            manager.unmarkTask(taskIndex-1);
        } catch(Exception e){
            System.out.println("Thats not it. Please provide a valid task number to unmark.");
        }
    }

    private void handleMark(String input){
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            manager.markTask(taskIndex-1);
        } catch(Exception e){
            System.out.println("Thats not it. Please provide a valid task number to mark.");
        }
    }

    private void handleDelete(String input){
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            manager.deleteTask(taskIndex-1);
        } catch(Exception e){
            System.out.println("Thats not it. Please provide a valid task number to delete.");
        }
    }
    private void handleFind(String input){
        try {
            String keyword = input.substring(5).trim(); // remove find command from search string
            if (keyword.isEmpty()) {
                System.out.println("Please provide a keyword to search for!");
                return;
            }
            manager.findTasks(keyword);
        } catch(Exception e){
            System.out.println("Oops! Something went wrong while searching mate.");
        }
    }
}
