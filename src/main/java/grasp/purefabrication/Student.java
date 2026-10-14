package grasp.purefabrication;

import common.Console;

/**
 * GRASP / Pure Fabrication smell (the absence of one).
 *
 * <p>{@code Student} is a domain entity, but it also carries its own persistence
 * (hand-built SQL, a static "connection") and its own outbound e-mail. Two extra
 * reasons to change, a hard dependency on a database dialect, and the domain rule
 * {@link #isEligibleForDeansList()} cannot be exercised without all of it.
 */
public class Student {

    /** Merely loading this class "connects". */
    private static final FakeConnection CONNECTION =
            FakeConnection.open("jdbc:postgresql://db.chnu.edu.ua:5432/registry");

    private final long id;
    private final String fullName;
    private final String email;
    private final String programme;
    private final double gpa;

    public Student(long id, String fullName, String email, String programme, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.programme = programme;
        this.gpa = gpa;
    }

    // --- domain behaviour ------------------------------------------------
    public boolean isEligibleForDeansList() {
        return gpa >= 3.75;
    }

    public String fullName() {
        return fullName;
    }

    // --- persistence, welded onto the entity --------------------------
    public void saveToDatabase() {
        String sql = "INSERT INTO students(id, full_name, email, programme, gpa) VALUES ("
                + id + ", '" + fullName + "', '" + email + "', '" + programme + "', " + gpa + ")";
        CONNECTION.execute(sql);
    }

    // --- outbound e-mail, also welded on -----------------------------
    public void emailWelcome() {
        Console.step("SMTP -> " + email + " | Welcome to " + programme + ", " + fullName + "!");
    }

    /** Tiny stand-in for a JDBC connection. */
    static final class FakeConnection {
        private FakeConnection(String url) {
            Console.note("FakeConnection: opened " + url);
        }

        static FakeConnection open(String url) {
            return new FakeConnection(url);
        }

        void execute(String sql) {
            Console.note("SQL> " + sql);
        }
    }
}
