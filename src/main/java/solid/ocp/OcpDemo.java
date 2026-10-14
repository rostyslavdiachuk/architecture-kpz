package solid.ocp;

import common.Console;

public class OcpDemo {

    public static void main(String[] args) {
        Console.header("SOLID / OCP -- grading schemes as strategies");

        FinalGradeCalculator calc = new FinalGradeCalculator();

        report(calc, "STANDARD", new StandardScheme(), 87);
        report(calc, "PASS_FAIL", new PassFailScheme(), 58);
        report(calc, "HONORS", new HonorsScheme(), 95);
        report(calc, "ECTS", new EctsScheme(), 66);

        Console.header("New requirement: master's-thesis defence uses the THESIS scheme");
        Console.note("Added ThesisScheme.java only -- FinalGradeCalculator was not touched.");
        report(calc, "THESIS", new ThesisScheme(), 92);
        report(calc, "THESIS", new ThesisScheme(), 40);

        Console.ok("Every scheme answers letterGrade / gpaPoints / label consistently; "
                + "there is no 'default' arm left to get wrong.");
    }

    private static void report(FinalGradeCalculator calc, String name, GradingScheme scheme, int score) {
        Console.ok(name + " @ " + score + "  ->  letter=" + calc.letterGrade(scheme, score)
                + ", gpa=" + calc.gpaPoints(scheme, score)
                + ", transcript=\"" + calc.transcriptLabel(scheme, score) + "\"");
    }
}
