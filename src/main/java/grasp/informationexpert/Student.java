package grasp.informationexpert;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String id;
    private final String fullName;
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> enrollments() {
        return enrollments;
    }
}
