package solid.isp;

import common.Console;

import java.util.List;

public class IspDemo {

    public static void main(String[] args) {
        Console.header("SOLID / ISP -- one fat UniversityMember interface");

        List<UniversityMember> campus = List.of(
                new Lecturer("Dr. Hryhoriy Ostapenko", true),
                new Student("Sofia Marchenko"),
                new Student("Dmytro Kravets"));

        Console.step("TermClosingJob asks every campus member to submit grades for CS201");
        new TermClosingJob().collectGrades(campus, "CS201");

        Console.step("Campus portal asks every member for a transcript");
        for (UniversityMember m : campus) {
            try {
                m.requestTranscript();
            } catch (UnsupportedOperationException e) {
                Console.fail("requestTranscript on " + m.fullName() + ": " + e.getMessage());
            }
        }

        Console.header("Why it broke");
        Console.fail("Student is forced to implement teachCourse/submitGrades/... and "
                + "Lecturer to implement payTuition/requestTranscript/...");
        Console.fail("Every client must guard every call with try/catch.");
        Console.note("Refactor task: split into role interfaces (Teacher, Learner, "
                + "ThesisSupervisor, BudgetApprover, TranscriptRequester); each client "
                + "depends only on the role it needs.");
    }
}
