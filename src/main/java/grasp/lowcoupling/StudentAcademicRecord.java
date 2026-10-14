package grasp.lowcoupling;

import java.util.List;

/**
 * The one thing the audit needs from "the student side", regardless of whether
 * it is backed by Postgres, LDAP, a mainframe or a list.
 */
public interface StudentAcademicRecord {

    String studentName();

    List<String> passedCourses();

    int earnedCredits();

    double gpa();

    boolean hasOutstandingIncomplete();

    /** Adapts the plain {@link Student} record to this role. */
    static StudentAcademicRecord of(Student s) {
        return new StudentAcademicRecord() {
            @Override public String studentName() { return s.fullName(); }
            @Override public List<String> passedCourses() { return s.passedCourseCodes(); }
            @Override public int earnedCredits() { return s.earnedCredits(); }
            @Override public double gpa() { return s.gpa(); }
            @Override public boolean hasOutstandingIncomplete() { return s.hasOutstandingIncomplete(); }
        };
    }
}
