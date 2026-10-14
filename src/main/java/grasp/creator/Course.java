package grasp.creator;

import java.util.ArrayList;
import java.util.List;

/**
 * The course is the creator: it aggregates enrollments and knows its capacity,
 * so it builds the {@link Enrollment} and files it on its own roster in one
 * step. The roster is no longer handed out for external mutation.
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

    public Enrollment enroll(Student student, Semester semester) {
        if (remainingSeats() <= 0) {
            throw new IllegalStateException(code + " is full");
        }
        Enrollment enrollment = new Enrollment(student, this, semester);
        roster.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> roster() {
        return List.copyOf(roster);
    }

    public int remainingSeats() {
        return capacity - roster.size();
    }
}
