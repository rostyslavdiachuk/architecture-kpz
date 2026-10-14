package grasp.protectedvariations;

/**
 * A third copy of the thresholds. Here "First Class" was typed as 88 by mistake,
 * and merit funding keys off it.
 */
public class ScholarshipCommittee {

    public boolean qualifiesForMeritFunding(int finalScore) {
        return classification(finalScore).equals("First Class");
    }

    public String classification(int finalScore) {
        if (finalScore >= 88) return "First Class";
        if (finalScore >= 75) return "Upper Second Class";
        if (finalScore >= 60) return "Lower Second Class";
        return "Fail";
    }
}
