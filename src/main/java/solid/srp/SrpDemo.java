package solid.srp;

import common.Console;

import java.util.List;
import java.util.Set;

public class SrpDemo {

    public static void main(String[] args) {
        Console.header("SOLID / SRP -- one enrollment 'god class'");

        StudentEnrollmentService service = new StudentEnrollmentService();
        service.seedStudent(new Student("S-01", "Olena Kovalenko", "olena@chnu.edu.ua",
                Set.of("CS101", "MATH101"), 20));
        service.seedStudent(new Student("S-02", "Petro Bondar", "petro@chnu.edu.ua",
                Set.of(), 28));

        Course algorithms = new Course("CS201", "Algorithms & Data Structures", 6,
                List.of("CS101"), 800);
        Course databases = new Course("CS202", "Database Systems", 6, List.of("CS101"), 800);

        Console.step("Olena enrolls in CS201 (has the prerequisite, within the cap)");
        service.enroll("S-01", algorithms);

        Console.step("Petro enrolls in CS201 (missing prerequisite CS101)");
        service.enroll("S-02", algorithms);

        Console.step("Olena enrolls in CS202 (would push her term load over 30 credits)");
        service.enroll("S-01", databases);

        Console.header("Registrar opens the audit trail");
        List<String> audit = service.auditTrail();
        audit.forEach(Console::note);
        Console.fail("2 of the 3 attempts were rejected; the audit trail has only "
                + audit.size() + " line(s) and not one of the rejections -- they are invisible");
        Console.note("The audit call lives inside the letter-formatting block, so it only "
                + "runs on the happy path.");
        Console.note("Refactor task: give persistence, rules, pricing, formatting, e-mail "
                + "and audit each their own class; keep this service as a thin orchestrator.");
    }
}
