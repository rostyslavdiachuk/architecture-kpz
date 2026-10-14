package solid.lsp;

import common.Console;

import java.util.List;

/**
 * Overnight job: enroll each student into their requested section.
 *
 * <p>It is written against the {@link CourseSection} contract and treats every
 * section the same way -- which is exactly what LSP is supposed to allow.
 */
public class RegistrationBatch {

    public record Request(CourseSection section, Student student) {
    }

    public void run(List<Request> requests) {
        for (Request r : requests) {
            try {
                r.section().enroll(r.student());
            } catch (SectionFullException e) {
                Console.warn(r.student().fullName() + " -> " + r.section().code()
                        + ": section full, try another");
                continue;
            } catch (RuntimeException e) {
                // the base contract permits only SectionFullException
                Console.fail(r.student().fullName() + " -> " + r.section().code()
                        + ": enroll() threw " + e.getClass().getSimpleName()
                        + " (\"" + e.getMessage() + "\") -- not part of the CourseSection contract");
                continue;
            }
            // per the contract, the student must now be on the roster
            if (r.section().roster().contains(r.student())) {
                Console.ok(r.student().fullName() + " enrolled in " + r.section().code());
            } else {
                Console.fail(r.student().fullName() + " NOT on the roster of "
                        + r.section().code() + " even though enroll() returned normally");
            }
        }
    }
}
