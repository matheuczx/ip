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
            System.out.println(i+1 + ". " + (this.taskList[i]).getDescription());
            System.out.println("____________________________________________________________");
        }
    }
}
