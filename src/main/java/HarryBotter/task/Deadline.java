package HarryBotter.task;
/**
 * Represents a task with a specific deadline.
 * A Deadline is a type of Task that includes a due date
 * indicating when the task should be completed.
 */

public class Deadline extends Task{
    private String dueDate;

    public Deadline(String description, String dueDate){
        super(description);
        this.dueDate = dueDate;
    }

    public String getDueDate(){
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
