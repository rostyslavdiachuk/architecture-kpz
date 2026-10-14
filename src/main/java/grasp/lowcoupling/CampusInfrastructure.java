package grasp.lowcoupling;

import common.Console;

import java.util.List;
import java.util.Map;

/**
 * Six unrelated concrete infrastructure classes. Each is a stand-in; what
 * matters is that {@link DegreeAuditService} names all six by their concrete
 * type and builds them itself.
 */
public final class CampusInfrastructure {

    private CampusInfrastructure() {
    }

    public static class PostgresCourseCatalog {
        public PostgresCourseCatalog() {
            Console.note("PostgresCourseCatalog: jdbc:postgresql://.../catalog");
        }

        public int creditsFor(String courseCode) {
            return 5;
        }
    }

    public static class LdapStudentDirectory {
        private final Map<String, Student> people;

        public LdapStudentDirectory(Map<String, Student> people) {
            this.people = people;
            Console.note("LdapStudentDirectory: bound to ldap://ad.chnu.edu.ua");
        }

        public Student lookup(String id) {
            return people.get(id);
        }
    }

    public static class LegacyGradeMainframeClient {
        public LegacyGradeMainframeClient() {
            Console.note("LegacyGradeMainframeClient: opening 3270 session to GRADES01");
        }

        public boolean hasOutstandingIncomplete(String studentId) {
            return false;
        }
    }

    public static class EmailBlastService {
        public EmailBlastService() {
            Console.note("EmailBlastService: warming up bulk mailer");
        }

        public void send(String to, String message) {
            Console.step("SMTP -> " + to + " | " + message);
        }
    }

    public static class PdfDiplomaRenderer {
        public PdfDiplomaRenderer() {
            Console.note("PdfDiplomaRenderer: loading LaTeX templates");
        }

        public String render(String studentName, String programme) {
            return "[PDF diploma: " + studentName + " / " + programme + "]";
        }
    }

    public static class RegistrarEventBus {
        public RegistrarEventBus() {
            Console.note("RegistrarEventBus: connecting to amqp://mq.chnu.edu.ua");
        }

        public void publish(String topic, String payload) {
            Console.note("event " + topic + ": " + payload);
        }
    }

    /** Convenience seed used by the demo. */
    public static List<String> coreProgrammeCourses() {
        return List.of("CS101", "CS201", "CS202", "MATH201", "CS301");
    }
}
