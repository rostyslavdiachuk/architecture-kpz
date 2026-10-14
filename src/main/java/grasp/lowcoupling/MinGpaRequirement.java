package grasp.lowcoupling;

import java.util.Optional;

public class MinGpaRequirement implements DegreeRequirement {

    private final double minGpa;

    public MinGpaRequirement(double minGpa) {
        this.minGpa = minGpa;
    }

    @Override
    public Optional<String> unmetReason(StudentAcademicRecord record) {
        return record.gpa() >= minGpa
                ? Optional.empty()
                : Optional.of("GPA " + record.gpa() + " below " + minGpa);
    }
}
