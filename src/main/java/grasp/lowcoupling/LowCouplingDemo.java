package grasp.lowcoupling;

import java.util.List;

import common.Console;

public class LowCouplingDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Low Coupling -- DegreeAuditService depends on 2 abstractions");

        DegreeRequirements bscComputerScience = new DegreeRequirements(List.of(
                new RequiredCoursesRequirement(List.of("CS101", "CS201", "CS202", "MATH201", "CS301")),
                new MinGpaRequirement(3.5),
                new MinCreditsRequirement(180),
                new NoOutstandingIncompleteRequirement()));

        DegreeAuditService service = new DegreeAuditService(
                bscComputerScience, new ConsoleGraduationGateway());

        Student vira = new Student("S-51", "Vira Datsenko",
                List.of("CS101", "CS201", "CS202", "MATH201", "CS301"), 190, 3.8, false);
        Student andriy = new Student("S-52", "Andriy Poluden",
                List.of("CS101", "CS201", "MATH201"), 140, 3.1, false);

        Console.step("Audit S-51");
        service.audit(StudentAcademicRecord.of(vira), "BSc Computer Science");
        Console.step("Audit S-52");
        service.audit(StudentAcademicRecord.of(andriy), "BSc Computer Science");

        Console.ok("The service names no infrastructure: swapping the data source or the "
                + "diploma/mail/event stack does not touch it.");
        Console.ok("Adding a rule = adding a DegreeRequirement to the list.");
    }
}
