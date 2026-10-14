package grasp.protectedvariations;

/**
 * GRASP / Protected Variations smell: the classification thresholds are a known
 * point of instability (the ministry revises them), yet they are hard-coded here
 * -- and separately in {@link HonorsCommittee} and {@link ScholarshipCommittee}.
 */
public class TranscriptPrinter {

    public String classification(int finalScore) {
        if (finalScore >= 90) return "First Class";
        if (finalScore >= 75) return "Upper Second Class";
        if (finalScore >= 60) return "Lower Second Class";
        return "Fail";
    }

    public String line(String studentName, int finalScore) {
        return studentName + " -- " + finalScore + " -- " + classification(finalScore);
    }
}
