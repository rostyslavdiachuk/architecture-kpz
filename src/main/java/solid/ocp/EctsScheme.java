package solid.ocp;

public class EctsScheme implements GradingScheme {

    @Override
    public String letterGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 65) return "D";
        if (score >= 60) return "E";
        return "FX";
    }

    @Override
    public double gpaPoints(int score) {
        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }

    @Override
    public String label(int score) {
        return "ECTS grade: " + letterGrade(score);
    }
}
