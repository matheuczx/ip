package HarryBotter.task;

public class Event extends Task{
    private String startDateTime;
    private String endDateTime;

    public Event(String description, String startDateTime, String endDateTime){
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime + " to: " + this.endDateTime + ")";
    }
}
