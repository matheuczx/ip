package HarryBotter.task;
/**
 * Represents an event task that occurs within a specific time frame.
 * An Event is a type of Task that includes both a start date/time
 * and an end date/time to indicate its duration.
 */

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
