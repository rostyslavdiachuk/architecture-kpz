package solid.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Shared state for every kind of section: an identifier and a roster. It no
 * longer promises {@code enroll} or {@code scheduleRoom} -- those live on the
 * subtypes that can actually honour them.
 */
public abstract class CourseSection implements Enrollable {

    private final String code;
    protected final List<Student> roster = new ArrayList<>();

    protected CourseSection(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public List<Student> roster() {
        return List.copyOf(roster);
    }
}
