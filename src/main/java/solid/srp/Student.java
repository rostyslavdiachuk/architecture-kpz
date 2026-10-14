package solid.srp;

import java.util.LinkedHashSet;
import java.util.Set;

/** University student record. */
public class Student {

    private final String id;
    private final String fullName;
    private final String email;
    private final Set<String> completedCourseCodes = new LinkedHashSet<>();
    private int enrolledCredits;

    public Student(String id, String fullName, String email, Set<String> completedCourseCodes, int enrolledCredits) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.completedCourseCodes.addAll(completedCourseCodes);
        this.enrolledCredits = enrolledCredits;
    }

    public String id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public Set<String> completedCourseCodes() {
        return completedCourseCodes;
    }

    public int enrolledCredits() {
        return enrolledCredits;
    }

    public void addEnrolledCredits(int credits) {
        this.enrolledCredits += credits;
    }
}
