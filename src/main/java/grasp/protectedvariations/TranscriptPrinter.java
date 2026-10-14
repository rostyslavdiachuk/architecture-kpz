package grasp.protectedvariations;

/** Depends on the policy interface, not on any threshold numbers. */
public class TranscriptPrinter {

    private final GradeClassificationPolicy policy;

    public TranscriptPrinter(GradeClassificationPolicy policy) {
        this.policy = policy;
    }

    public String classification(int finalScore) {
        return policy.classify(finalScore);
    }

    public String line(String studentName, int finalScore) {
        return studentName + " -- " + finalScore + " -- " + classification(finalScore);
    }
}
