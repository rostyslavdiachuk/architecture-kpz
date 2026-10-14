package grasp.protectedvariations;

/** Merit funding keys off the same classification everyone else sees. */
public class ScholarshipCommittee {

    private final GradeClassificationPolicy policy;

    public ScholarshipCommittee(GradeClassificationPolicy policy) {
        this.policy = policy;
    }

    public boolean qualifiesForMeritFunding(int finalScore) {
        return "First Class".equals(policy.classify(finalScore));
    }

    public String classification(int finalScore) {
        return policy.classify(finalScore);
    }
}
