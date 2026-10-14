package grasp.controller;

import grasp.controller.Infrastructure.AuditLog;
import grasp.controller.Infrastructure.CourseCatalog;
import grasp.controller.Infrastructure.EmailNotifier;
import grasp.controller.Infrastructure.StudentDirectory;

/**
 * The single entry point for the "register a student for a course" system
 * operation. Every UI calls this; the lookup / capacity / roster / e-mail /
 * audit sequence exists exactly once.
 */
public class RegistrationController {

    public record RegisterCommand(String studentId, String courseCode) {
    }

    public record RegistrationResult(boolean success, String message) {
    }

    private final StudentDirectory students;
    private final CourseCatalog courses;
    private final EmailNotifier email;
    private final AuditLog audit;

    public RegistrationController(StudentDirectory students, CourseCatalog courses,
                                 EmailNotifier email, AuditLog audit) {
        this.students = students;
        this.courses = courses;
        this.email = email;
        this.audit = audit;
    }

    public RegistrationResult registerStudentForCourse(RegisterCommand cmd) {
        Student student = students.find(cmd.studentId());
        Course course = courses.find(cmd.courseCode());
        if (student == null || course == null) {
            return new RegistrationResult(false, "unknown student or course");
        }
        if (course.remainingSeats() <= 0) {
            audit.record("REG-DENIED " + cmd.studentId() + " -> " + cmd.courseCode() + " (full)");
            return new RegistrationResult(false, course.code() + " is full");
        }
        course.addStudent(student.id());
        email.send(student.email(), "You are registered for " + course.code());
        audit.record("REG " + student.id() + " -> " + course.code());
        return new RegistrationResult(true, "registered " + student.fullName() + " for " + course.code());
    }
}
