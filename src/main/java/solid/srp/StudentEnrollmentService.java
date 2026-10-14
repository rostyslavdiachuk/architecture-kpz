package solid.srp;

import common.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SRP smell: one class with many reasons to change.
 *
 * <p>{@link #enroll} alone does persistence, prerequisite + credit-limit rules,
 * tuition pricing, letter formatting, e-mail delivery and audit logging. Every
 * one of those is a different stakeholder: the registrar, the dean of studies,
 * the bursar, the marketing office, the IT mail team and the internal auditor.
 *
 * <p>Concrete consequence shown by the demo: the audit line is written in the
 * middle of the "format + send letter" block, so a <b>rejected</b> enrollment
 * leaves no audit trace at all.
 */
public class StudentEnrollmentService {

    private final Map<String, Student> studentTable = new HashMap<>();
    private final List<String> auditTable = new ArrayList<>();

    public void seedStudent(Student student) {
        studentTable.put(student.id(), student);
    }

    public List<String> auditTrail() {
        return auditTable;
    }

    public void enroll(String studentId, Course course) {
        // --- persistence: load the row -------------------------------------
        Student student = studentTable.get(studentId);
        if (student == null) {
            Console.fail("no student row for id=" + studentId);
            return;
        }

        // --- business rule: prerequisites --------------------------------
        for (String prereq : course.prerequisiteCodes()) {
            if (!student.completedCourseCodes().contains(prereq)) {
                Console.fail(student.fullName() + " is missing prerequisite " + prereq
                        + " for " + course.code() + " -> rejected");
                return; // note: no audit line written on this path
            }
        }

        // --- business rule: credit-load cap -----------------------------
        int maxCreditsPerTerm = 30;
        if (student.enrolledCredits() + course.credits() > maxCreditsPerTerm) {
            Console.fail(student.fullName() + " would exceed the " + maxCreditsPerTerm
                    + "-credit term cap -> rejected");
            return; // note: no audit line written on this path either
        }

        // --- pricing: tuition for this course --------------------------
        int tuition = course.credits() * course.pricePerCredit();
        if (student.completedCourseCodes().size() >= 10) {
            tuition = (int) Math.round(tuition * 0.9); // senior-student discount
        }

        // --- persistence: update the row ------------------------------
        student.addEnrolledCredits(course.credits());
        studentTable.put(student.id(), student);

        // --- presentation + I/O + audit, all tangled together -------
        String letter = "Dear " + student.fullName() + ",\n"
                + "  You are now enrolled in " + course.code() + " \"" + course.title() + "\".\n"
                + "  Tuition due: " + tuition + " UAH.\n"
                + "  Regards, Registrar's Office";
        Console.step("SMTP -> " + student.email() + "\n" + indent(letter));
        auditTable.add("ENROLLED " + student.id() + " " + course.code() + " tuition=" + tuition);
        Console.ok("enrolled " + student.fullName() + " in " + course.code()
                + " (term credits now " + student.enrolledCredits() + ")");
    }

    private static String indent(String block) {
        StringBuilder sb = new StringBuilder();
        for (String line : block.split("\n")) {
            sb.append("      | ").append(line).append('\n');
        }
        return sb.toString().stripTrailing();
    }
}
