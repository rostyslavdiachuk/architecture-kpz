package solid.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * A capped section with an explicit waitlist. It honours the contract: when the
 * roster is full {@code enroll} throws {@link SectionFullException} -- it never
 * pretends to have enrolled someone. The waitlist is a separate, visible
 * operation.
 */
public class WaitlistedSection extends CourseSection {

    private final int capacity;
    private final List<Student> waitlist = new ArrayList<>();

    public WaitlistedSection(String code, int capacity) {
        super(code);
        this.capacity = capacity;
    }

    @Override
    public void enroll(Student student) {
        if (roster.size() >= capacity) {
            throw new SectionFullException(code() + " is full; joinWaitlist() is available");
        }
        roster.add(student);
    }

    public void joinWaitlist(Student student) {
        waitlist.add(student);
    }

    public List<Student> waitlist() {
        return List.copyOf(waitlist);
    }
}
