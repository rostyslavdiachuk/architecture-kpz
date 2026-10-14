package grasp.creator;

import java.util.ArrayList;
import java.util.List;

import common.Console;

/**
 * Still useful as a cross-course index, but it no longer <em>creates</em>
 * enrollments -- it asks the {@link Course} to, so the roster can never be out
 * of sync.
 */
public class EnrollmentManager {

    private final List<Enrollment> allEnrollments = new ArrayList<>();

    public Enrollment enroll(Student student, Course course, Semester semester) {
        Enrollment enrollment = course.enroll(student, semester);
        allEnrollments.add(enrollment);
        Console.note("enrolled " + student.fullName() + " -> " + course.code()
                + " (" + semester.term() + "); course roster now " + course.roster().size());
        return enrollment;
    }

    public List<Enrollment> allEnrollments() {
        return allEnrollments;
    }
}
