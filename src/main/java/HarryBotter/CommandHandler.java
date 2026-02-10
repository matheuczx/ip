package HarryBotter;

import HarryBotter.UI.UserInterface;

public class CommandHandler {
    private TaskManager manager;
    private UserInterface userInterface;

    public CommandHandler(TaskManager manager, UserInterface userInterface){
        this.manager =  manager;
        this.userInterface = userInterface;
    }

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
            else{
                manager.addTask(input);
            }
        } catch (HarryBotterException e){
            userInterface.showError(e.getMessage());
        }

        return false;
    }

    private void handleUnmark(String input){
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            manager.unmarkTask(taskIndex-1);
        } catch(Exception e){
            System.out.println("Thats not it. Please provide a valid task number to mark.");
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
}
