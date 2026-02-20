package HarryBotter;

import HarryBotter.UI.UserInterface;
import HarryBotter.storage.Storage;
import HarryBotter.task.Task;

import java.io.IOException;

/**
 * HarryBotter is a simple chatbot
 * Features a wannabe British personality.
 */

public class HarryBotter {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        Storage storage = new Storage("./data/harrybotter.txt");
        TaskManager manager = new TaskManager(storage);

        // Load tasks at startup
        Task[] loadedTasks = storage.load();
        manager.setTasks(loadedTasks);  // add a setter in TaskManager
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
