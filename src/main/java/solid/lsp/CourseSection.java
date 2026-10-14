package solid.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for a section of a course.
 *
 * <p>Contract that callers rely on:
 * <ul>
 *   <li>{@code enroll(s)} either adds {@code s} to {@link #roster()} or throws
 *       {@link SectionFullException} -- nothing else;</li>
 *   <li>{@code scheduleRoom(room)} assigns a physical room.</li>
 * </ul>
 * The subclasses in this package break that contract.
 */
public class CourseSection {

    private final String code;
    private final int capacity;
    private final List<Student> roster = new ArrayList<>();
    private String room = "unassigned";

    public CourseSection(String code, int capacity) {
        this.code = code;
        this.capacity = capacity;
    }

    public String code() {
        return code;
    }

    public List<Student> roster() {
        return roster;
    }

    public int remainingSeats() {
        return capacity - roster.size();
    }

    public void enroll(Student student) {
        if (roster.size() >= capacity) {
            throw new SectionFullException(code + " is full");
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
