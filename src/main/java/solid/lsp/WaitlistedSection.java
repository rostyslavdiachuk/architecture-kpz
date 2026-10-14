package solid.lsp;

/**
 * LSP violation: weakens the postcondition.
 *
 * <p>The base contract says {@code enroll} adds the student or throws. This
 * subclass, when full, does <b>neither</b> -- it just returns, quietly moving the
 * student to an (imaginary) waitlist. Callers believe the enrollment succeeded.
 */
public class WaitlistedSection extends CourseSection {

    public WaitlistedSection(String code, int capacity) {
        super(code, capacity);
    }

    @Override
    public void enroll(Student student) {
        if (remainingSeats() <= 0) {
            // silently "waitlisted" -- no add, no throw
            return;
        }
        super.enroll(student);
    }
}
