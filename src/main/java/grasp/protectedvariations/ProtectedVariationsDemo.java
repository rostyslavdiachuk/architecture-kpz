package grasp.protectedvariations;

import common.Console;

public class ProtectedVariationsDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Protected Variations -- classification thresholds copied 3x");

        String student = "Myroslav Haiduk";
        int finalScore = 90;
        double gpa = 3.72;

        String onTranscript = new TranscriptPrinter().classification(finalScore);
        HonorsCommittee honors = new HonorsCommittee();
        String byHonors = honors.classification(finalScore);
        ScholarshipCommittee scholarship = new ScholarshipCommittee();

        Console.step(student + " finished with a final score of " + finalScore);
        Console.note("Transcript prints        : " + onTranscript);
        Console.note("Honours committee decides : " + byHonors);
        Console.note("Latin honours (gpa " + gpa + "): " + honors.latinHonors(gpa));
        Console.note("Merit funding qualifies   : " + scholarship.qualifiesForMeritFunding(finalScore));

        Console.fail("Three official outputs, three different threshold tables -- the "
                + "transcript and the honours letter already disagree at score 90.");
        Console.note("Next year the ministry raises 'First Class' to 92: you must find and "
                + "edit every copy, and the demo shows one is already wrong.");
        Console.note("Refactor task: one stable GradeClassificationPolicy interface "
                + "(classify(score), latinHonors(gpa)); Policy2024 / Policy2026 chosen once; "
                + "every consumer depends on the interface.");
    }
}
