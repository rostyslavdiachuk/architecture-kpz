package solid.ocp;

/**
 * Now closed for modification: it delegates to whichever {@link GradingScheme}
 * it is given and never needs a new branch again.
 */
public class FinalGradeCalculator {

    public String letterGrade(GradingScheme scheme, int score) {
        return scheme.letterGrade(score);
    }

    public double gpaPoints(GradingScheme scheme, int score) {
        return scheme.gpaPoints(score);
    }

    public String transcriptLabel(GradingScheme scheme, int score) {
        return scheme.label(score);
    }
}
