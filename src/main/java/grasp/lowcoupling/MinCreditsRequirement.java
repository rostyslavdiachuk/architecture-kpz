package grasp.lowcoupling;

import java.util.Optional;

public class MinCreditsRequirement implements DegreeRequirement {

    private final int minCredits;

    public MinCreditsRequirement(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public Optional<String> unmetReason(StudentAcademicRecord record) {
        return record.earnedCredits() >= minCredits
                ? Optional.empty()
                : Optional.of(record.earnedCredits() + " credits, needs " + minCredits);
    }
}
