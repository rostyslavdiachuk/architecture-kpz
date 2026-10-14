package solid.isp;

import common.Console;
import solid.isp.Roles.Teacher;
import solid.isp.Roles.ThesisSupervisor;

/** A lecturer teaches and supervises theses -- and nothing else here. */
public class Lecturer implements Teacher, ThesisSupervisor {

    private final String fullName;

    public Lecturer(String fullName) {
        this.fullName = fullName;
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
}
