package solid.dip;

import java.time.LocalDate;

import common.Console;

public class DipDemo {

    public static void main(String[] args) {
        Console.header("SOLID / DIP -- ScholarshipService depends on injected abstractions");

        InMemoryScholarshipRepository repository = new InMemoryScholarshipRepository();
        repository.add(new ScholarshipApplication("S-01", "olena@chnu.edu.ua", 3.9, 6000));
        repository.add(new ScholarshipApplication("S-02", "petro@chnu.edu.ua", 3.2, 4200));
        repository.add(new ScholarshipApplication("S-03", "iryna@chnu.edu.ua", 3.7, 21000));

        ScholarshipService service = new ScholarshipService(
                repository,
                new ConsoleNotificationGateway(),
                new FixedClock(LocalDate.of(2026, 9, 2)));

        Console.step("awardMeritAndNeedScholarships(minGpa=3.5, incomeCeiling=15000)");
        service.awardMeritAndNeedScholarships(3.5, 15_000);

        Console.ok("No database connection, no SMTP session -- the policy ran on a list, a "
                + "console and a fixed date.");
        Console.note("In production, pass PostgresScholarshipDatabase + SmtpEmailClient + "
                + "SystemClock instead: same service, no code change.");
    }
}
