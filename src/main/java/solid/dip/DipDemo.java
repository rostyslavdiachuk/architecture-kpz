package solid.dip;

import common.Console;

public class DipDemo {

    public static void main(String[] args) {
        Console.header("SOLID / DIP -- ScholarshipService news up its own infrastructure");

        Console.step("new ScholarshipService()  -- watch what just constructing it does:");
        ScholarshipService service = new ScholarshipService();

        Console.step("awardMeritAndNeedScholarships(minGpa=3.5, incomeCeiling=15000)");
        service.awardMeritAndNeedScholarships(3.5, 15_000);

        Console.header("Why this hurts");
        Console.fail("Creating the policy object already 'connected to Postgres' and "
                + "'opened an SMTP session' -- you cannot run it in a lecture, a test, or "
                + "offline.");
        Console.fail("Switching to a file store or a different mailer means editing "
                + "ScholarshipService.");
        Console.note("Refactor task: depend on abstractions -- ScholarshipRepository, "
                + "NotificationGateway, Clock -- injected through the constructor; provide "
                + "in-memory / console / fixed implementations and wire them here in main.");
    }
}
