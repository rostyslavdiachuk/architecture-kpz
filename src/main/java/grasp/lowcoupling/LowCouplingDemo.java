package grasp.lowcoupling;

import common.Console;

import java.util.List;
import java.util.Map;

public class LowCouplingDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Low Coupling -- DegreeAuditService wired to 6 concrete classes");

        Map<String, Student> people = Map.of(
                "S-51", new Student("S-51", "Vira Datsenko",
                        List.of("CS101", "CS201", "CS202", "MATH201", "CS301"), 190, 3.8),
                "S-52", new Student("S-52", "Andriy Poluden",
                        List.of("CS101", "CS201", "MATH201"), 140, 3.1));

        Console.step("Construct the service (watch the six dependencies wake up):");
        DegreeAuditService service = new DegreeAuditService(
                people, CampusInfrastructure.coreProgrammeCourses(), 3.5, 180);

        Console.step("Audit S-51");
        service.audit("S-51", "BSc Computer Science");
        Console.step("Audit S-52");
        service.audit("S-52", "BSc Computer Science");

        Console.header("Why this hurts");
        Console.fail("DegreeAuditService names 6 concrete infrastructure types and builds "
                + "them itself -- swap the catalog for a REST client and you edit this class.");
        Console.fail("It also runs the degree rules on Student's raw fields (feature envy).");
        Console.note("Refactor task: depend on 2 abstractions -- StudentAcademicRecord and "
                + "DegreeRequirements (a list of self-checking DegreeRequirement objects); "
                + "push infra behind adapters, injected in.");
    }
}
