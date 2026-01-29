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

    public void addTask(String taskDescription){
        if (taskCount >= taskList.length) {
            System.out.println("Cannot add more tasks! Maximum reached.");
            return;
        }
        Task newTask = createTask(taskDescription);
        this.taskList[taskCount] = newTask;
        taskCount ++;
    }

    public void listTasks(){
        System.out.println("Here ya go!");
        System.out.println("____________________________________________________________");

        for(int i = 0; i<taskCount; i++){
            System.out.print(i+1 + "." );
            if(this.taskList[i].isDone()){
                System.out.print("[X] ");
            }
            else{
                System.out.print("[ ] ");
            }
            System.out.println((this.taskList[i]).getDescription());
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
        System.out.println("[X] " + taskList[taskIndex].getDescription());
        System.out.println("____________________________________________________________");
    }

    public void unmarkTask(int taskIndex){
        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number!");
            return;
        }
        this.taskList[taskIndex].updateStatus(false);
        System.out.println("OK mate, I've marked this task as not done yet:");
        System.out.println("[ ] " + taskList[taskIndex].getDescription());
        System.out.println("____________________________________________________________");
    }
}
