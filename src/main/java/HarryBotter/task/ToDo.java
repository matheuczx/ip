package HarryBotter.task;
/**
 * Represents a to-do task in the task management system.
 */

public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
