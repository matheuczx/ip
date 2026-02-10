package HarryBotter;

import HarryBotter.UI.UserInterface;

/**
 * HarryBotter is a simple chatbot
 * Features a wannabe British personality.
 */

public class HarryBotter {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        TaskManager manager = new TaskManager();
        CommandHandler commandHandler = new CommandHandler(manager, userInterface);

        userInterface.showWelcome();

        while(true){
            String input = userInterface.readCommand();
            boolean shouldExit = commandHandler.handleInput(input);

            if(shouldExit){
                userInterface.showExit();
                break;
            }
        }
    }
}
