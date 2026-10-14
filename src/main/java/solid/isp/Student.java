package solid.isp;

import common.Console;

/** A student forced to implement teaching / budget methods it has no use for. */
public class Student implements UniversityMember {

    private final String fullName;

    public Student(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public void enrollInCourse(String courseCode) {
        Console.ok(fullName + " enrolled in " + courseCode);
    }

    @Override
    public void payTuition(int amountUah) {
        Console.ok(fullName + " paid " + amountUah + " UAH tuition");
    }

    @Override
    public void requestTranscript() {
        Console.ok(fullName + " requested a transcript");
    }

    // --- methods that make no sense for a student ---

    @Override
    public void teachCourse(String courseCode) {
        throw new UnsupportedOperationException("a student cannot teach " + courseCode);
    }

    @Override
    public void submitGrades(String courseCode) {
        throw new UnsupportedOperationException("a student cannot submit grades for " + courseCode);
    }

    @Override
    public void holdOfficeHours() {
        throw new UnsupportedOperationException("a student does not hold office hours");
    }

    @Override
    public void superviseThesis(String studentId) {
        throw new UnsupportedOperationException("a student cannot supervise a thesis");
    }

    @Override
    public void approveDepartmentBudget(int amountUah) {
        throw new UnsupportedOperationException("a student cannot approve a budget");
    }
}
