/**
 * Manages a list of Task objects.
 * Supports adding, listing, marking, and unmarking tasks.
 */

public class TaskManager {
    private Task[] tasks;
    private int taskCount;
    private static final int MAX_TASKS = 100;
    private static final String LINE = "____________________________________________________________";

    //constructor
    public TaskManager(){
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public Task createTask(String taskDescription){
        return new Task(taskDescription);
    }

    private String[] parseDeadline(String input) {
        return input.split(" /by ", 2);
    }

    private String[] parseEvent(String input) {
        String[] eventTimeline = input.split(" /from ", 2);
        String[] timelineSplit = eventTimeline[1].split(" /to ",2);
        return new String[]{eventTimeline[0], timelineSplit[0], timelineSplit[1]};
    }

    /**
     * Adds a new task to the task list based on the user input.
     * Supports "todo", "deadline", and "event" task types.
     *
     * @param taskInput the input string entered by the user
     */
    public void addTask(String taskInput){
        if (taskCount >= tasks.length) {
            System.out.println("Cannot add more tasks! Maximum reached.");
            return;
        }

        Task newTask;
        String[] taskComponents = taskInput.split(" ",2);
        String taskType = taskComponents[0].toLowerCase();

        switch (taskType){
        case "todo":
            if(taskComponents.length<2){
                System.out.println("Enter a valid todo task!");
                return;
            }
            newTask = new ToDo(taskComponents[1]);
            break;

        case "deadline":
            if (taskComponents.length <2 || !taskComponents[1].contains(" /by ")){
                System.out.println("Deadline must be in format: description /by date");
                return;
            }
            String[] deadlineComponents = parseDeadline(taskComponents[1]);
            newTask  = new Deadline(deadlineComponents[0],deadlineComponents[1]);
            break;

        case "event":
            if (taskComponents.length<2 || !taskComponents[1].contains(" /from ") || !taskComponents[1].contains(" /to")){
                System.out.println("Deadline must be in format: description /from start /to end");
                return;
            }
            String[] eventParts = parseEvent(taskComponents[1]);
            newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            break;

        default:
            System.out.println("Unknown command! Use todo, deadline, or event. If there's nothing else, type bye.");
            return;
        }

        this.tasks[taskCount] = newTask;
        taskCount++;

        System.out.println("Alrighty! Added: " + System.lineSeparator() + newTask.toString());
    }

    public void listTasks(){
        System.out.println("Here ya go!");
        System.out.println(LINE);

        for(int i = 0; i<taskCount; i++){
            System.out.print(i+1 + "." );
            System.out.println(tasks[i].toString());
            System.out.println(LINE);
        }
    }

    public void markTask(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number!");
            return;
        }
        this.tasks[taskIndex].updateStatus(true);
        System.out.println("OK mate, I've marked this task as done!");
        System.out.println(tasks[taskIndex].toString());
        System.out.println(LINE);
    }

    public void unmarkTask(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number!");
            return;
        }
        this.tasks[taskIndex].updateStatus(false);
        System.out.println("OK mate, I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex].toString());
        System.out.println(LINE);
    }
}
