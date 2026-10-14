package grasp.controller;

import common.Console;
import grasp.controller.Infrastructure.AuditLog;
import grasp.controller.Infrastructure.CourseCatalog;
import grasp.controller.Infrastructure.EmailNotifier;
import grasp.controller.Infrastructure.StudentDirectory;

/**
 * The second entry point for the very same system operation. It was written
 * later, by copy-paste, and has drifted:
 * <ul>
 *   <li>no capacity check;</li>
 *   <li>no confirmation e-mail;</li>
 *   <li>a different audit string.</li>
 * </ul>
 */
public class RestRegistrationHandler {

    private final StudentDirectory students;
    private final CourseCatalog courses;
    private final EmailNotifier email; // held, but never used here
    private final AuditLog audit;

    public RestRegistrationHandler(StudentDirectory students, CourseCatalog courses,
                                   EmailNotifier email, AuditLog audit) {
        this.students = students;
        this.courses = courses;
        this.email = email;
        this.audit = audit;
    }

    /** Simulates  POST /api/registrations {studentId, courseCode}. */
    public void post(String studentId, String courseCode) {
        Student student = students.find(studentId);
        Course course = courses.find(courseCode);
        if (student == null || course == null) {
            Console.fail("rest: 404 unknown student or course");
            return;
        }
        course.addStudent(student.id());
        audit.record("enroll:" + course.code() + ":" + student.id());
        Console.ok("rest: 200 registered " + student.fullName() + " for " + course.code());
    }
}
