package solid.dip;

import java.time.LocalDate;

import common.Console;

/**
 * The high-level policy now depends only on abstractions it owns
 * ({@link ScholarshipRepository}, {@link NotificationGateway}, {@link Clock}),
 * handed in through the constructor. It mentions no database, no mailer, no wall
 * clock -- so it runs anywhere, including this demo and a unit test.
 */
public class ScholarshipService {

    private final ScholarshipRepository repository;
    private final NotificationGateway notifications;
    private final Clock clock;

    public ScholarshipService(ScholarshipRepository repository, NotificationGateway notifications, Clock clock) {
        this.repository = repository;
        this.notifications = notifications;
        this.clock = clock;
    }

    public void awardMeritAndNeedScholarships(double minGpa, int incomeCeilingUah) {
        LocalDate today = clock.today();
        int awardedCount = 0;
        for (ScholarshipApplication app : repository.findPending()) {
            boolean meritsIt = app.gpa() >= minGpa;
            boolean needsIt = app.monthlyFamilyIncomeUah() <= incomeCeilingUah;
            if (meritsIt && needsIt) {
                app.markAwarded();
                repository.update(app);
                notifications.notify(app.email(), "Scholarship awarded",
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
