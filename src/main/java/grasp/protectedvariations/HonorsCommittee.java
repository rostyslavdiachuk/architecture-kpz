package grasp.protectedvariations;

/** Same policy object as everyone else -- no private copy of the thresholds. */
public class HonorsCommittee {

    private final GradeClassificationPolicy policy;

    public HonorsCommittee(GradeClassificationPolicy policy) {
        this.policy = policy;
    }

    public String classification(int finalScore) {
        return policy.classify(finalScore);
    }

    public String latinHonors(double gpa) {
        return policy.latinHonors(gpa);
    }
}
