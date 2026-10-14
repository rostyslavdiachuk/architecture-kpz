package grasp.informationexpert;

import common.Console;

public class InformationExpertDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Information Expert -- the GPA lives with the data");

        Student student = new Student("S-20", "Kateryna Boyko");
        student.addEnrollment(new Enrollment(new Course("CS201", "Algorithms", 6), new Grade(95)));
        student.addEnrollment(new Enrollment(new Course("MATH201", "Probability Theory", 6), new Grade(91)));
        student.addEnrollment(new Enrollment(new Course("PHIL101", "Philosophy of Science", 2), new Grade(70)));

        double transcriptGpa = new TranscriptReportService().gpa(student);
        ScholarshipEligibilityService scholarship = new ScholarshipEligibilityService();

        Console.step("Both services now ask student.transcript().gpa():");
        Console.note("TranscriptReportService      -> " + transcriptGpa);
        Console.note("ScholarshipEligibilityService -> " + scholarship.gpa(student));
        Console.ok("One number. isEligible() (cutoff 3.60) -> " + scholarship.isEligible(student)
                + ", consistent with the transcript.");

        Console.note("Enrollment.qualityPoints() and Transcript.gpa() are the only places the "
                + "formula exists; new consumers cannot re-invent it.");
    }
}
