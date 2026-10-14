package solid.ocp;

public class PassFailScheme implements GradingScheme {

    @Override
    public String letterGrade(int score) {
        return score >= 60 ? "P" : "F";
    }

    @Override
    public double gpaPoints(int score) {
        return 0.0; // pass/fail does not affect the GPA
    }

    @Override
    public String label(int score) {
        return score >= 60 ? "Passed" : "Failed";
    }
}
