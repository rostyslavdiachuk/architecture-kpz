package grasp.creator;

import common.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP / Creator smell: a separate manager creates {@link Enrollment} objects.
 *
 * <p>It does not contain, aggregate or closely use enrollments, and it does not
 * hold the data an enrollment needs -- the {@link Course} does. Because creation
 * lives here, keeping the course roster in sync is a second, easily forgotten
 * step. This class keeps its own list and never touches the course.
 */
public class EnrollmentManager {

    private final List<Enrollment> allEnrollments = new ArrayList<>();

    public Enrollment createEnrollment(Student student, Course course, Semester semester) {
        Enrollment enrollment = new Enrollment(student, course, semester);
        allEnrollments.add(enrollment);
        Console.note("created enrollment: " + student.fullName() + " -> " + course.code()
                + " (" + semester.term() + ")");
        // forgot to do:  course.roster().add(enrollment);
        return enrollment;
    }

    public List<Enrollment> allEnrollments() {
        return allEnrollments;
    }
}
