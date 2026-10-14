package solid.dip;

import common.Console;

import java.time.LocalDate;

/**
 * DIP smell: a high-level policy that depends on low-level details.
 *
 * <p>The rule "who gets a merit-and-need scholarship" is business policy. But
 * this class {@code new}s its own Postgres connection, SMTP client and wall
 * clock, so the policy cannot run without that infrastructure and cannot be
 * exercised offline or in a test. There is no seam.
 */
public class ScholarshipService {

    private final PostgresScholarshipDatabase database;
    private final SmtpEmailClient email;
    private final SystemClock clock;

    public ScholarshipService() {
        // high-level module reaching down and wiring up low-level modules itself
        this.database = new PostgresScholarshipDatabase();
        this.email = new SmtpEmailClient();
        this.clock = new SystemClock();
    }

    public void awardMeritAndNeedScholarships(double minGpa, int incomeCeilingUah) {
        LocalDate today = clock.today();
        int awardedCount = 0;
        for (ScholarshipApplication app : database.findPendingApplications()) {
            boolean meritsIt = app.gpa() >= minGpa;
            boolean needsIt = app.monthlyFamilyIncomeUah() <= incomeCeilingUah;
            if (meritsIt && needsIt) {
                app.markAwarded();
                database.update(app);
                email.send(app.email(), "Scholarship awarded",
                        "Dear student, your scholarship was approved on " + today + ".");
                awardedCount++;
            } else {
                Console.note(app.studentId() + " not eligible (gpa " + app.gpa()
                        + ", income " + app.monthlyFamilyIncomeUah() + ")");
            }
        }
        Console.ok("awarded " + awardedCount + " scholarship(s)");
    }
}
