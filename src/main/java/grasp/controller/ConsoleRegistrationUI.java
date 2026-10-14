package grasp.controller;

import common.Console;
import grasp.controller.Infrastructure.AuditLog;
import grasp.controller.Infrastructure.CourseCatalog;
import grasp.controller.Infrastructure.EmailNotifier;
import grasp.controller.Infrastructure.StudentDirectory;

/**
 * GRASP / Controller smell: the UI class runs the whole system operation.
 *
 * <p>Looking up the student and course, checking capacity, updating the roster,
 * notifying and auditing are all coordinated here, in the presentation layer.
 * The REST entry point re-implements the same flow -- see
 * {@link RestRegistrationHandler} -- and the two have already drifted.
 */
public class ConsoleRegistrationUI {

    private final StudentDirectory students;
    private final CourseCatalog courses;
    private final EmailNotifier email;
    private final AuditLog audit;

    public ConsoleRegistrationUI(StudentDirectory students, CourseCatalog courses,
                                 EmailNotifier email, AuditLog audit) {
        this.students = students;
        this.courses = courses;
        this.email = email;
        this.audit = audit;
    }

    /** Simulates the operator picking "1) Register student for course". */
    public void onRegisterMenuOption(String studentId, String courseCode) {
        Student student = students.find(studentId);
        Course course = courses.find(courseCode);
        if (student == null || course == null) {
            Console.fail("console: unknown student or course");
            return;
        }
        if (course.remainingSeats() <= 0) {
            Console.fail("console: " + courseCode + " is full");
            return;
        }
        course.addStudent(student.id());
        email.send(student.email(), "You are registered for " + course.code());
        audit.record("REG " + student.id() + " -> " + course.code());
        Console.ok("console: registered " + student.fullName() + " for " + course.code());
    }
}
