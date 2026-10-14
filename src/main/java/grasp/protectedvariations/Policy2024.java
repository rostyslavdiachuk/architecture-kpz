package grasp.protectedvariations;

/** The thresholds in force since 2024. */
public class Policy2024 implements GradeClassificationPolicy {

    @Override
    public String classify(int finalScore) {
        if (finalScore >= 90) return "First Class";
        if (finalScore >= 75) return "Upper Second Class";
        if (finalScore >= 60) return "Lower Second Class";
        return "Fail";
    }

    @Override
    public String latinHonors(double gpa) {
        if (gpa >= 3.9) return "summa cum laude";
        if (gpa >= 3.7) return "magna cum laude";
        if (gpa >= 3.5) return "cum laude";
        return "";
    }
}
