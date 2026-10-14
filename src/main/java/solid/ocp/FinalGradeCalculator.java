package solid.ocp;

/**
 * OCP smell: not closed against new grading schemes.
 *
 * <p>The same {@code switch (scheme)} appears three times. Supporting a new
 * scheme means editing all three (and any others that appear later), and the
 * {@code default} arms disagree with each other:
 * <ul>
 *   <li>{@link #letterGrade} falls back to {@code "N/A"}</li>
 *   <li>{@link #gpaPoints} falls back to {@code 0.0} -- silently wrong, it drags
 *       the GPA down</li>
 *   <li>{@link #transcriptLabel} throws</li>
 * </ul>
 */
public class FinalGradeCalculator {

    public String letterGrade(GradingScheme scheme, int score) {
        switch (scheme) {
            case STANDARD:
            case HONORS:
                if (score >= 90) return "A";
                if (score >= 80) return "B";
                if (score >= 70) return "C";
                if (score >= 60) return "D";
                return "F";
            case PASS_FAIL:
                return score >= 60 ? "P" : "F";
            case ECTS:
                if (score >= 90) return "A";
                if (score >= 80) return "B";
                if (score >= 70) return "C";
                if (score >= 65) return "D";
                if (score >= 60) return "E";
                return "FX";
            default:
                return "N/A";
        }
    }

    public double gpaPoints(GradingScheme scheme, int score) {
        switch (scheme) {
            case STANDARD:
            case ECTS:
                if (score >= 90) return 4.0;
                if (score >= 80) return 3.0;
                if (score >= 70) return 2.0;
                if (score >= 60) return 1.0;
                return 0.0;
            case HONORS:
                // honours courses are weighted one point higher
                if (score >= 90) return 5.0;
                if (score >= 80) return 4.0;
                if (score >= 70) return 3.0;
                if (score >= 60) return 2.0;
                return 0.0;
            case PASS_FAIL:
                return score >= 60 ? 0.0 : 0.0; // pass/fail does not affect GPA
            default:
                return 0.0;
        }
    }

    public String transcriptLabel(GradingScheme scheme, int score) {
        switch (scheme) {
            case STANDARD:
                return "Standard grade: " + letterGrade(scheme, score);
            case HONORS:
                return "Honours grade: " + letterGrade(scheme, score);
            case ECTS:
                return "ECTS grade: " + letterGrade(scheme, score);
            case PASS_FAIL:
                return score >= 60 ? "Passed" : "Failed";
            default:
                throw new IllegalArgumentException("unknown grading scheme: " + scheme);
        }
    }
}
