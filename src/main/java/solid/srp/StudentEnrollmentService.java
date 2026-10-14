package solid.srp;

import java.util.Optional;

import common.Console;

/**
 * One responsibility: orchestrate an enrollment. Each other concern lives in its
 * own collaborator, so this class reads like a table of contents -- and the
 * audit log is now written on every path, including rejections.
 */
public class StudentEnrollmentService {

    private final StudentRepository students;
    private final EnrollmentPolicy policy;
    private final TuitionCalculator tuition;
    private final ConfirmationLetterFormatter letters;
    private final EmailNotifier email;
    private final AuditLog audit;

    public StudentEnrollmentService(StudentRepository students, EnrollmentPolicy policy,
                                    TuitionCalculator tuition, ConfirmationLetterFormatter letters,
                                    EmailNotifier email, AuditLog audit) {
        this.students = students;
        this.policy = policy;
        this.tuition = tuition;
        this.letters = letters;
        this.email = email;
        this.audit = audit;
    }

    public void enroll(String studentId, Course course) {
        Optional<Student> found = students.findById(studentId);
        if (found.isEmpty()) {
            Console.fail("no student row for id=" + studentId);
            return;
        }
        Student student = found.get();
        audit.record("ATTEMPT " + studentId + " " + course.code());

        Optional<String> rejection = policy.rejectionReason(student, course);
        if (rejection.isPresent()) {
            audit.record("REJECTED " + studentId + " " + course.code() + " (" + rejection.get() + ")");
            Console.fail(student.fullName() + ": " + rejection.get() + " -> rejected");
            return;
        }

        int due = tuition.tuitionFor(student, course);
        student.addEnrolledCredits(course.credits());
        students.save(student);

        email.send(student.email(), letters.format(student, course, due));
        audit.record("ENROLLED " + studentId + " " + course.code() + " tuition=" + due);
        Console.ok("enrolled " + student.fullName() + " in " + course.code()
                + " (term credits now " + student.enrolledCredits() + ")");
    }
}
