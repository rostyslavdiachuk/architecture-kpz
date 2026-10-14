package solid.lsp;

/**
 * LSP violation: strengthens preconditions / throws a new exception type.
 *
 * <p>An online section has no seat limit and no room, so it "cannot" implement
 * {@code enroll} / {@code scheduleRoom} the way the base class promises -- it
 * throws {@link UnsupportedOperationException} instead. Any code holding a
 * {@code CourseSection} reference now breaks when handed one of these.
 */
public class SelfPacedOnlineSection extends CourseSection {

    public SelfPacedOnlineSection(String code) {
        super(code, Integer.MAX_VALUE);
    }

    @Override
    public void enroll(Student student) {
        throw new UnsupportedOperationException(
                code() + " is self-paced online; call join() instead of enroll()");
    }

    @Override
    public void scheduleRoom(String room) {
        throw new UnsupportedOperationException(code() + " is online; it has no room");
    }

    /** The "real" API this class wants callers to use. */
    public void join(Student student) {
        roster().add(student);
    }
}
