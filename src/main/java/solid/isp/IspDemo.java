package solid.isp;

import java.util.List;

import common.Console;
import solid.isp.Roles.BudgetApprover;
import solid.isp.Roles.Teacher;
import solid.isp.Roles.TranscriptRequester;

public class IspDemo {

    public static void main(String[] args) {
        Console.header("SOLID / ISP -- role interfaces, each client takes what it needs");

        DepartmentHead head = new DepartmentHead("Dr. Hryhoriy Ostapenko");
        Lecturer lecturer = new Lecturer("Dr. Solomiya Veres");
        Student sofia = new Student("Sofia Marchenko");
        Student dmytro = new Student("Dmytro Kravets");

        Console.step("TermClosingJob takes List<Teacher> -- students are not even a candidate");
        new TermClosingJob().collectGrades(List.<Teacher>of(head, lecturer), "CS201");

        Console.step("Campus portal takes List<TranscriptRequester>");
        for (TranscriptRequester r : List.<TranscriptRequester>of(sofia, dmytro)) {
            r.requestTranscript();
        }

        Console.step("Budget approval asks only for a BudgetApprover");
        BudgetApprover approver = head;
        approver.approveDepartmentBudget(250_000);

        Console.ok("No UnsupportedOperationException anywhere; no client guards a call. "
                + "The compiler rejects 'student submits grades' before it can run.");
    }
}
