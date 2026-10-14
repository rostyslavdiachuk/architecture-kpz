package grasp.protectedvariations;

/**
 * A second copy of the thresholds -- already drifted from
 * {@link TranscriptPrinter}: "First Class" starts at 91 here, not 90.
 */
public class HonorsCommittee {

    public String classification(int finalScore) {
        if (finalScore >= 91) return "First Class";
        if (finalScore >= 76) return "Upper Second Class";
        if (finalScore >= 61) return "Lower Second Class";
        return "Fail";
    }

    public String latinHonors(double gpa) {
        if (gpa >= 3.9) return "summa cum laude";
        if (gpa >= 3.7) return "magna cum laude";
        if (gpa >= 3.5) return "cum laude";
        return "";
    }
}
