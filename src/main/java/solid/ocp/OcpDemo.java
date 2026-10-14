package solid.ocp;

import common.Console;

public class OcpDemo {

    public static void main(String[] args) {
        Console.header("SOLID / OCP -- final-grade calculator with a switch per scheme");

        FinalGradeCalculator calc = new FinalGradeCalculator();

        report(calc, GradingScheme.STANDARD, 87);
        report(calc, GradingScheme.PASS_FAIL, 58);
        report(calc, GradingScheme.HONORS, 95);
        report(calc, GradingScheme.ECTS, 66);

        Console.header("New requirement: master's-thesis defence uses the THESIS scheme");
        Console.note("Rule: score >= 75 -> \"Defended\" and 4.0 GPA points, otherwise "
                + "\"Not defended\" and 0.0.");
        Console.step("Student defended with score 92 -- ask the existing calculator:");

        String letter = calc.letterGrade(GradingScheme.THESIS, 92);
        double gpa = calc.gpaPoints(GradingScheme.THESIS, 92);
        Console.fail("letterGrade -> \"" + letter + "\"  (default arm: bogus letter)");
        Console.fail("gpaPoints   -> " + gpa + "  (default arm: silently zero, wrecks the GPA)");
        try {
            calc.transcriptLabel(GradingScheme.THESIS, 92);
        } catch (RuntimeException e) {
            Console.fail("transcriptLabel -> throws: " + e.getMessage());
        }

        Console.note("One new scheme, three files to touch, three different failure modes.");
        Console.note("Refactor task: make GradingScheme a strategy interface so a new "
                + "scheme is a new class and the calculator never changes.");
    }

    private static void report(FinalGradeCalculator calc, GradingScheme scheme, int score) {
        Console.ok(scheme + " @ " + score + "  ->  letter=" + calc.letterGrade(scheme, score)
                + ", gpa=" + calc.gpaPoints(scheme, score)
                + ", transcript=\"" + calc.transcriptLabel(scheme, score) + "\"");
    }
}
