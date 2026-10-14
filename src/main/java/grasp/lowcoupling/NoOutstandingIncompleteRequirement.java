package grasp.lowcoupling;

import java.util.Optional;

public class NoOutstandingIncompleteRequirement implements DegreeRequirement {

    @Override
    public Optional<String> unmetReason(StudentAcademicRecord record) {
        return record.hasOutstandingIncomplete()
                ? Optional.of("has an outstanding incomplete grade")
                : Optional.empty();
    }
}
