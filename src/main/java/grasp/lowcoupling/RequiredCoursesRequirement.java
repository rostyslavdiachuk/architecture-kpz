package grasp.lowcoupling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequiredCoursesRequirement implements DegreeRequirement {

    private final List<String> requiredCourses;

    public RequiredCoursesRequirement(List<String> requiredCourses) {
        this.requiredCourses = List.copyOf(requiredCourses);
    }

    @Override
    public Optional<String> unmetReason(StudentAcademicRecord record) {
        List<String> missing = new ArrayList<>();
        for (String code : requiredCourses) {
            if (!record.passedCourses().contains(code)) {
                missing.add(code);
            }
        }
        return missing.isEmpty() ? Optional.empty() : Optional.of("missing courses " + missing);
    }
}
