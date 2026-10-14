package solid.lsp;

/** The only exception a well-behaved {@link CourseSection#enroll} may throw. */
public class SectionFullException extends RuntimeException {
    public SectionFullException(String message) {
        super(message);
    }
}
