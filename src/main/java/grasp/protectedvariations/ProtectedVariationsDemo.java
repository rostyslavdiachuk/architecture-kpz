package grasp.protectedvariations;

import common.Console;

public class ProtectedVariationsDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Protected Variations -- one policy behind every consumer");

        String student = "Myroslav Haiduk";
        int finalScore = 90;
        double gpa = 3.72;

        showUnder("2024 rules", new Policy2024(), student, finalScore, gpa);
        showUnder("2026 rules (ministry revision)", new Policy2026(), student, finalScore, gpa);

        Console.ok("Every consumer moves together because they all read the same "
                + "GradeClassificationPolicy. A Policy2027 would be one new file, zero "
                + "consumer edits.");
    }

    private static void showUnder(String label, GradeClassificationPolicy policy,
                                  String student, int finalScore, double gpa) {
        TranscriptPrinter transcript = new TranscriptPrinter(policy);
        HonorsCommittee honors = new HonorsCommittee(policy);
        ScholarshipCommittee scholarship = new ScholarshipCommittee(policy);

        Console.step(label + " -- " + student + " @ " + finalScore);
        Console.note("Transcript        : " + transcript.classification(finalScore));
        Console.note("Honours committee : " + honors.classification(finalScore));
        Console.note("Merit funding     : " + scholarship.qualifiesForMeritFunding(finalScore));
        Console.note("Latin honours     : " + honors.latinHonors(gpa));
    }
}
