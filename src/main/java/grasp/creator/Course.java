package grasp.creator;

import java.util.ArrayList;
import java.util.List;

/**
 * The course aggregates its enrollments and knows its own capacity -- it has
 * everything needed to create an {@link Enrollment}. Yet here it only exposes
 * its internal list for someone else to mutate.
 */
public class Course {

    private final String code;
    private final int capacity;
    private final List<Enrollment> roster = new ArrayList<>();

    public Course(String code, int capacity) {
        this.code = code;
        this.capacity = capacity;
    }

    public String code() {
        return code;
    }

    /** Leaky: callers are trusted to add the enrollments they create. */
    public List<Enrollment> roster() {
        return roster;
    }

    public int remainingSeats() {
        return capacity - roster.size();
    }
}
