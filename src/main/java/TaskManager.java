public class TaskManager {
    private Task[] taskList;
    private int taskCount;

    TaskManager(){
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public Task createTask(String taskDescription){
        Task newTask = new Task(taskDescription);
        return newTask;
    }

    public void addTask(String taskDescription){
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
        this.taskList[taskIndex].setDone(true);
        System.out.println("OK mate, I've marked this task as done!");
        System.out.println("[X] " + taskList[taskIndex].getDescription());
        System.out.println("____________________________________________________________");
    }

    public void unmarkTask(int taskIndex){
        this.taskList[taskIndex].setDone(false);
        System.out.println("OK mate, I've marked this task as not done yet:");
        System.out.println("[ ] " + taskList[taskIndex].getDescription());
        System.out.println("____________________________________________________________");
    }
}
