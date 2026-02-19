package HarryBotter;

import HarryBotter.task.Deadline;
import HarryBotter.task.Event;
import HarryBotter.task.Task;
import HarryBotter.task.ToDo;
import java.util.ArrayList;

/**
 * Manages a list of Task objects.
 * Supports adding, listing, marking, and unmarking tasks.
 */

public class TaskManager {
    private ArrayList<Task> tasks;
    //private Task[] tasks;
    //private int taskCount;
    //private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________________________";

    //constructor
    public TaskManager(){
        this.tasks = new ArrayList<>();
    //    this.taskCount = 0;
    }

    public Task createTask(String taskDescription){
        return new Task(taskDescription);
    }

    private String[] parseDeadline(String input) {
        return input.split(" /by ", 2);
    }

    private String[] parseEvent(String input) throws HarryBotterException{
        if(!input.contains(" /from") || !input.contains("/to")){
            throw new HarryBotterException(
                    "Hey mate, event must be in the format: event <desc> /from <start> /to <end>"
            );
        }
        String[] eventTimeline = input.split(" /from ", 2);

        if (eventTimeline.length < 2 || eventTimeline[0].isBlank()) {
            throw new HarryBotterException("Hey mate, what event are you adding?");
        }

        String[] timelineSplit = eventTimeline[1].split(" /to ",2);

        if (timelineSplit.length < 2 || timelineSplit[0].isBlank() || timelineSplit[1].isBlank()) {
            throw new HarryBotterException(
                    "Yo, when does your event start and end?"
            );
        }
        return new String[]{eventTimeline[0], timelineSplit[0], timelineSplit[1]};
    }

    /**
     * Adds a new task to the task list based on the user input.
     * Supports "todo", "deadline", and "event" task types.
     *
     * @param taskInput the input string entered by the user
     */
    public void addTask(String taskInput) throws HarryBotterException{
        /*
        if (taskCount >= tasks.length) {
            System.out.println("Cannot add more tasks! Maximum reached.");
            return;
        }
         */

        Task newTask;
        String[] taskComponents = taskInput.split(" ",2);
        String taskType = taskComponents[0].toLowerCase();

        switch (taskType){
        case "todo":
            if(taskComponents.length <2 || taskComponents[1].isBlank()){
                throw new HarryBotterException("Come on, add a task description mate!");
            }
            newTask = new ToDo(taskComponents[1]);
            break;

        case "deadline":
            if (taskComponents.length < 2 || taskComponents[1].isBlank()) {
                throw new HarryBotterException("M8... the description of a deadline cannot be empty.");
            }
            String[] deadlineComponents = parseDeadline(taskComponents[1]);
            if (deadlineComponents.length < 2 || deadlineComponents[1].isBlank()) {
                throw new HarryBotterException("add a the description or date mate!");
            }
            newTask  = new Deadline(deadlineComponents[0],deadlineComponents[1]);
            break;

        case "event":
            if (taskComponents.length<2 || !taskComponents[1].contains(" /from ") || !taskComponents[1].contains(" /to")){
                System.out.println("Hey man, deadline must be in format: description /from start /to end");
                return;
            }
            String[] eventParts = parseEvent(taskComponents[1]);
            newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;

        default:
            throw new HarryBotterException(
                    "What did you say? Use todo, deadline, or event. If there's nothing else, type bye."
            );
        }

        //this.tasks[taskCount] = newTask;
        //taskCount++;
        tasks.add(newTask);

        System.out.println("Alrighty! Added: " + System.lineSeparator() + newTask.toString());
    }

    public void listTasks(){
        System.out.println("Here ya go!");
        System.out.println(LINE);

        for(int i = 0; i<tasks.size(); i++){
            System.out.print(i+1 + "." );
            System.out.println(tasks.get(i).toString());
            System.out.println(LINE);
        }
    }

    public void markTask(int taskIndex) throws HarryBotterException{
        if (taskIndex<0 || taskIndex >=tasks.size()){
            throw new HarryBotterException("Invalid task number mate!");
        }
        this.tasks.get(taskIndex).updateStatus(true);
        System.out.println("OK mate, I've marked this task as done!");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(LINE);
    }

    public void unmarkTask(int taskIndex) throws HarryBotterException{
        if (taskIndex<0 || taskIndex >=tasks.size()){
            throw new HarryBotterException("Invalid task number mate!");
        }
        tasks.get(taskIndex).updateStatus(false);
        System.out.println("OK mate, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(LINE);
    }
/*
    public void deleteTask(int taskIndex) throws HarryBotterException{
        if (taskIndex<0 || taskIndex >= tasks.size()){
            throw new HarryBotterException("Choose an valid task number to delete mate!");
        }
        System.out.println("Sure mate, I have removed this task for you: ");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println("Congrats! Now you have" + tasks.size() + "tasks left to complete!");
        System.out.println(LINE);
    }

 */
}
