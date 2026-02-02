public class Event extends ToDo{
    private String startDateTime;
    private String endDateTime;

    public Event(String description, String startDateTime, String endDateTime){
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime +
                " to: " + this.endDateTime + ")";
    }
}
