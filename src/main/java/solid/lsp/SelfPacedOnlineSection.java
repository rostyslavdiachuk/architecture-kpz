package solid.lsp;

/**
 * An online section: no room, no seat limit. It honours the {@link Enrollable}
 * contract fully -- {@code enroll} always adds the student -- so it is
 * substitutable everywhere an {@code Enrollable} is expected.
 */
public class SelfPacedOnlineSection extends CourseSection {

    public SelfPacedOnlineSection(String code) {
        super(code);
    }

    @Override
    public void enroll(Student student) {
        roster.add(student);
    }
}
