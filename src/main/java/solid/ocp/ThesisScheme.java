package solid.ocp;

/**
 * The new scheme. It was added by writing <em>only</em> this file --
 * {@link FinalGradeCalculator} and the other schemes were not touched.
 */
public class ThesisScheme implements GradingScheme {

    private static final int DEFENCE_PASS_MARK = 75;

    @Override
    public String letterGrade(int score) {
        return score >= DEFENCE_PASS_MARK ? "Defended" : "Not defended";
    }

    @Override
    public double gpaPoints(int score) {
        return score >= DEFENCE_PASS_MARK ? 4.0 : 0.0;
    }

    @Override
    public String label(int score) {
        return "Thesis defence: " + letterGrade(score);
    }
}
