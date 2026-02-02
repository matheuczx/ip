/**
 * Represents a task with a description and completion status.
 */

public class Task {

    private String description;
    private boolean isDone;

    //constructor
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }
    //updates status of task
    public void updateStatus(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return (isDone() ? "[X] " : "[ ] ") + getDescription();
    }
}
