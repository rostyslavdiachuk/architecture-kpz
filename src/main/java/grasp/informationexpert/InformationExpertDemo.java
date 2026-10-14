package grasp.informationexpert;

import common.Console;

public class InformationExpertDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Information Expert -- GPA computed away from the data");

        Student student = new Student("S-20", "Kateryna Boyko");
        student.addEnrollment(new Enrollment(new Course("CS201", "Algorithms", 6), new Grade(95)));
        student.addEnrollment(new Enrollment(new Course("MATH201", "Probability Theory", 6), new Grade(91)));
        student.addEnrollment(new Enrollment(new Course("PHIL101", "Philosophy of Science", 2), new Grade(70)));

        double transcriptGpa = new TranscriptReportService().gpa(student);
        ScholarshipEligibilityService scholarship = new ScholarshipEligibilityService();
        double scholarshipGpa = scholarship.gpa(student);

        Console.step("Two services compute " + student.fullName() + "'s GPA:");
        Console.note("TranscriptReportService      -> " + transcriptGpa + "  (credit-weighted)");
        Console.note("ScholarshipEligibilityService -> " + scholarshipGpa + "  (unweighted)");

        Console.step("Scholarship cutoff is 3.60");
        Console.note("Transcript would show the student as ABOVE the cutoff.");
        Console.fail("ScholarshipEligibilityService.isEligible() -> " + scholarship.isEligible(student)
                + "  -- the office denies a scholarship the transcript implies.");

        Console.header("Why it broke");
        Console.fail("Neither Enrollment (has grade + credits) nor a Transcript owns the "
                + "GPA rule, so two teams implemented it differently.");
        Console.note("Refactor task: Enrollment.qualityPoints(); a Transcript that owns the "
                + "enrollment list computes gpa(); every service just asks the transcript.");
    }
}
