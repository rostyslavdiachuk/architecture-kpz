package solid.lsp;

import java.util.List;

import common.Console;

/**
 * Overnight job: enroll each student into their requested section. It depends
 * only on {@link Enrollable} and every implementation honours the contract, so
 * the single {@code catch (SectionFullException)} is all it needs -- no
 * type checks, no surprise exceptions.
 */
public class RegistrationBatch {

    public record Request(Enrollable section, Student student) {
    }

    public void run(List<Request> requests) {
        for (Request r : requests) {
            try {
                r.section().enroll(r.student());
            } catch (SectionFullException e) {
                Console.warn(r.student().fullName() + " -> " + r.section().code()
                        + ": " + e.getMessage() + " (not enrolled)");
                continue;
            }
            // guaranteed by the contract
            Console.ok(r.student().fullName() + " enrolled in " + r.section().code());
        }
    }
}
