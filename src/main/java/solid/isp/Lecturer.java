package solid.isp;

import common.Console;

/** A lecturer forced to implement tuition / enrollment methods it has no use for. */
public class Lecturer implements UniversityMember {

    private final String fullName;
    private final boolean departmentHead;

    public Lecturer(String fullName, boolean departmentHead) {
        this.fullName = fullName;
        this.departmentHead = departmentHead;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public void teachCourse(String courseCode) {
        Console.ok(fullName + " is teaching " + courseCode);
    }

    @Override
    public void submitGrades(String courseCode) {
        Console.ok(fullName + " submitted grades for " + courseCode);
    }

    @Override
    public void holdOfficeHours() {
        Console.ok(fullName + " is holding office hours");
    }

    @Override
    public void superviseThesis(String studentId) {
        Console.ok(fullName + " supervises thesis of " + studentId);
    }

    @Override
    public void approveDepartmentBudget(int amountUah) {
        if (!departmentHead) {
            throw new UnsupportedOperationException(fullName + " is not a department head");
        }
        Console.ok(fullName + " approved a department budget of " + amountUah + " UAH");
    }

    // --- methods that make no sense for a lecturer ---

    @Override
    public void enrollInCourse(String courseCode) {
        throw new UnsupportedOperationException("a lecturer does not enroll in " + courseCode);
    }

    @Override
    public void payTuition(int amountUah) {
        throw new UnsupportedOperationException("a lecturer does not pay tuition");
    }

    @Override
    public void requestTranscript() {
        throw new UnsupportedOperationException("a lecturer has no student transcript");
    }
}
