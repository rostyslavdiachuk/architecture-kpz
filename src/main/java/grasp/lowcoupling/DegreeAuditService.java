package grasp.lowcoupling;

import common.Console;
import grasp.lowcoupling.CampusInfrastructure.EmailBlastService;
import grasp.lowcoupling.CampusInfrastructure.LdapStudentDirectory;
import grasp.lowcoupling.CampusInfrastructure.LegacyGradeMainframeClient;
import grasp.lowcoupling.CampusInfrastructure.PdfDiplomaRenderer;
import grasp.lowcoupling.CampusInfrastructure.PostgresCourseCatalog;
import grasp.lowcoupling.CampusInfrastructure.RegistrarEventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GRASP / Low Coupling smell.
 *
 * <p>This one class is bound to <b>six</b> concrete infrastructure types, builds
 * every one of them in its constructor, and does the degree rules itself by
 * pulling raw fields off {@link Student} (feature envy). A change to any of the
 * six -- or to how "passed a course" is decided -- reaches in here.
 */
public class DegreeAuditService {

    private final PostgresCourseCatalog catalog;
    private final LdapStudentDirectory directory;
    private final LegacyGradeMainframeClient mainframe;
    private final EmailBlastService mailer;
    private final PdfDiplomaRenderer diploma;
    private final RegistrarEventBus eventBus;

    private final List<String> requiredCourses;
    private final double minGpa;
    private final int minCredits;

    public DegreeAuditService(Map<String, Student> people, List<String> requiredCourses,
                              double minGpa, int minCredits) {
        this.catalog = new PostgresCourseCatalog();
        this.directory = new LdapStudentDirectory(people);
        this.mainframe = new LegacyGradeMainframeClient();
        this.mailer = new EmailBlastService();
        this.diploma = new PdfDiplomaRenderer();
        this.eventBus = new RegistrarEventBus();
        this.requiredCourses = requiredCourses;
        this.minGpa = minGpa;
        this.minCredits = minCredits;
    }

    public boolean audit(String studentId, String programme) {
        Student s = directory.lookup(studentId);
        if (s == null) {
            Console.fail("no such student " + studentId);
            return false;
        }

        // rule logic done here, on someone else's data
        List<String> missing = new ArrayList<>();
        for (String code : requiredCourses) {
            if (!s.passedCourseCodes().contains(code)) {
                missing.add(code);
            }
        }
        boolean creditsOk = s.earnedCredits() >= minCredits;
        boolean gpaOk = s.gpa() >= minGpa;
        boolean noIncomplete = !mainframe.hasOutstandingIncomplete(studentId);
        int firstCourseCredits = catalog.creditsFor(requiredCourses.get(0)); // token use of the catalog

        boolean cleared = missing.isEmpty() && creditsOk && gpaOk && noIncomplete;
        Console.note(s.fullName() + ": missing=" + missing + ", creditsOk=" + creditsOk
                + ", gpaOk=" + gpaOk + ", noIncomplete=" + noIncomplete
                + ", sampleCourseCredits=" + firstCourseCredits);

        if (cleared) {
            String pdf = diploma.render(s.fullName(), programme);
            mailer.send(studentId + "@chnu.edu.ua", "Congratulations! " + pdf);
            eventBus.publish("degree.cleared", studentId);
            Console.ok(s.fullName() + " cleared for graduation");
        } else {
            eventBus.publish("degree.blocked", studentId);
            Console.fail(s.fullName() + " NOT cleared");
        }
        return cleared;
    }
}
