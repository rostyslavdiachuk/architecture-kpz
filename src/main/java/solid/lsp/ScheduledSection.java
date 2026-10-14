package solid.lsp;

/** An on-campus section: it has a seat limit and a room. */
public class ScheduledSection extends CourseSection {

    private final int capacity;
    private String room = "unassigned";

    public ScheduledSection(String code, int capacity) {
        super(code);
        this.capacity = capacity;
    }

    public int remainingSeats() {
        return capacity - roster.size();
    }

    @Override
    public void enroll(Student student) {
        if (roster.size() >= capacity) {
            throw new SectionFullException(code() + " is full");
        }
        roster.add(student);
    }

    public void scheduleRoom(String room) {
        this.room = room;
    }

    public String room() {
        return room;
    }
}
