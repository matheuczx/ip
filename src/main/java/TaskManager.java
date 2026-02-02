/**
 * Manages a list of Task objects.
 * Supports adding, listing, marking, and unmarking tasks.
 */

public class TaskManager {
    private Task[] taskList;
    private int taskCount;
    //constructor
    public TaskManager(){
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public Task createTask(String taskDescription){
        return new Task(taskDescription);
    }

    public void addTask(String taskInput){
        if (taskCount >= taskList.length) {
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
            if(taskComponents.length <2 || !taskComponents[1].contains(" /by ")){
                System.out.println("Deadline must be in format: description /by date");
                return;
            }
            String[] deadlineComponents = taskComponents[1].split(" /by ",2);
            newTask  = new Deadline(deadlineComponents[0],deadlineComponents[1]);
            break;

        case "event":
            if(taskComponents.length<2 || !taskComponents[1].contains(" /from ") || !taskComponents[1].contains(" /to")){
                System.out.println("Deadline must be in format: description /from start /to end");
                return;
            }
            // Split task type from event timeline
            String[] eventTimeline = taskComponents[1].split(" /from ", 2);
            String eventDescription = eventTimeline[0];

            // Split from and to
            String[] timelineSplit = eventTimeline[1].split(" /to ",2);
            String eventStart = timelineSplit[0];
            String eventEnd = timelineSplit[1];

            newTask = new Event(eventDescription, eventStart, eventEnd);
            break;

        default:
            System.out.println("Unknown command! Use todo, deadline, or event. If there's nothing else, type bye.");
            return;
        }

        this.taskList[taskCount] = newTask;
        taskCount ++;

        System.out.println("Alrighty! Added: " + System.lineSeparator() + newTask.toString());
    }

    public void listTasks(){
        System.out.println("Here ya go!");
        System.out.println("____________________________________________________________");

        for(int i = 0; i<taskCount; i++){
            System.out.print(i+1 + "." );
            System.out.println(taskList[i].toString());
            System.out.println("____________________________________________________________");
        }
    }

    public void markTask(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number!");
            return;
        }
        this.taskList[taskIndex].updateStatus(true);
        System.out.println("OK mate, I've marked this task as done!");
        System.out.println(taskList[taskIndex].toString());
        System.out.println("____________________________________________________________");
    }

    public void unmarkTask(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number!");
            return;
        }
        this.taskList[taskIndex].updateStatus(false);
        System.out.println("OK mate, I've marked this task as not done yet:");
        System.out.println(taskList[taskIndex].toString());
        System.out.println("____________________________________________________________");
    }
}
