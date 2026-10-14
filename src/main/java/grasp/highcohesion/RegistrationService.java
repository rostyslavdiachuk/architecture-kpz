package grasp.highcohesion;

import java.util.HashMap;
import java.util.Map;

import common.Console;
import grasp.highcohesion.Domain.Student;

/** One job: enrolling students. Its own id sequence. */
public class RegistrationService {

    private int nextStudentNumber = 1;
    private final Map<String, Student> students = new HashMap<>();

    public Student registerStudent(String fullName) {
        String id = "STU-" + String.format("%04d", nextStudentNumber++);
        Student s = new Student(id, fullName);
        students.put(id, s);
        Console.ok("registered " + fullName + " as " + id);
        return s;
    }

    public int registeredCount() {
        return students.size();
    }
}
