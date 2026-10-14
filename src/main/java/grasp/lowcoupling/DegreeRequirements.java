package grasp.lowcoupling;

import java.util.ArrayList;
import java.util.List;

/** A programme's rule set. Ask it what a record fails. */
public class DegreeRequirements {

    private final List<DegreeRequirement> rules;

    public DegreeRequirements(List<DegreeRequirement> rules) {
        this.rules = List.copyOf(rules);
    }

    public List<String> unmetReasons(StudentAcademicRecord record) {
        List<String> reasons = new ArrayList<>();
        for (DegreeRequirement rule : rules) {
            rule.unmetReason(record).ifPresent(reasons::add);
        }
        return reasons;
    }
}
