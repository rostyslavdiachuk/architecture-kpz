package solid.srp;

import java.util.List;
import java.util.Set;

import common.Console;

public class SrpDemo {

    public static void main(String[] args) {
        Console.header("SOLID / SRP -- enrollment split into single-purpose collaborators");

        StudentRepository students = new StudentRepository();
        AuditLog audit = new AuditLog();
        StudentEnrollmentService service = new StudentEnrollmentService(
                students,
                new EnrollmentPolicy(30),
                new TuitionCalculator(),
                new ConfirmationLetterFormatter(),
                new EmailNotifier(),
                audit);

        students.save(new Student("S-01", "Olena Kovalenko", "olena@chnu.edu.ua",
                Set.of("CS101", "MATH101"), 20));
        students.save(new Student("S-02", "Petro Bondar", "petro@chnu.edu.ua",
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
        audit.lines().forEach(Console::note);
        Console.ok("every attempt is recorded -- the 2 rejections are right there, because "
                + "AuditLog is its own collaborator called on every path");
        Console.note("Each concern -- persistence, rules, pricing, formatting, e-mail, audit "
                + "-- now changes in isolation.");
    }
}
