package grasp.protectedvariations;

/**
 * The 2026 ministry revision: "First Class" raised to 92, "Upper Second" to 78.
 * Written as one new file; no consumer changes.
 */
public class Policy2026 implements GradeClassificationPolicy {

    @Override
    public String classify(int finalScore) {
        if (finalScore >= 92) return "First Class";
        if (finalScore >= 78) return "Upper Second Class";
        if (finalScore >= 60) return "Lower Second Class";
        return "Fail";
    }

    @Override
    public String latinHonors(double gpa) {
        if (gpa >= 3.92) return "summa cum laude";
        if (gpa >= 3.75) return "magna cum laude";
        if (gpa >= 3.55) return "cum laude";
        return "";
    }
}
