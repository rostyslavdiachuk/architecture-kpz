package grasp.controller;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private final String code;
    private final int capacity;
    private final List<String> roster = new ArrayList<>();

    public Course(String code, int capacity) {
        this.code = code;
        this.capacity = capacity;
    }

    public String code() {
        return code;
    }

    public int remainingSeats() {
        return capacity - roster.size();
    }

    public void addStudent(String studentId) {
        roster.add(studentId);
    }

    public List<String> roster() {
        return List.copyOf(roster);
    }
}
