package grasp.purefabrication;

/**
 * Pure domain now: state and domain behaviour, nothing else. No SQL, no static
 * connection, no e-mail. It can be created and reasoned about on its own.
 */
public class Student {

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

    public boolean isEligibleForDeansList() {
        return gpa >= 3.75;
    }

    public long id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public String programme() {
        return programme;
    }

    public double gpa() {
        return gpa;
    }
}
