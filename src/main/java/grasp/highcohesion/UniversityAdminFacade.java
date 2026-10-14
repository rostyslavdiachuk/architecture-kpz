package grasp.highcohesion;

import common.Console;
import grasp.highcohesion.Domain.Book;
import grasp.highcohesion.Domain.Classroom;
import grasp.highcohesion.Domain.Lecturer;
import grasp.highcohesion.Domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GRASP / High Cohesion smell: one class, five unrelated jobs.
 *
 * <p>Registration, payroll, the library, room scheduling and a facilities
 * safety report share nothing but this class. None of the fields are used
 * together. And {@code nextSequenceNumber} is (lazily) reused as a student id, a
 * booking reference and a payslip number, so a rule change for one accidentally
 * moves the others.
 */
public class UniversityAdminFacade {

    private int nextSequenceNumber = 1000;

    private final Map<String, Student> students = new HashMap<>();
    private final List<String> payslips = new ArrayList<>();
    private final Map<String, String> shelvedBooks = new HashMap<>();
    private final Map<String, String> roomBookings = new HashMap<>();
    private int fireExtinguishersChecked;

    // --- registration ------------------------------------------------------
    public Student registerStudent(String fullName) {
        String id = "STU-" + (nextSequenceNumber++);
        Student s = new Student(id, fullName);
        students.put(id, s);
        Console.ok("registered " + fullName + " as " + id);
        return s;
    }

    // --- payroll ---------------------------------------------------------
    public void runPayroll(List<Lecturer> lecturers) {
        for (Lecturer l : lecturers) {
            String payslipNo = "PS-" + (nextSequenceNumber++);
            payslips.add(payslipNo);
            Console.ok("payslip " + payslipNo + ": " + l.fullName() + " " + l.monthlySalaryUah() + " UAH");
        }
    }

    // --- library -------------------------------------------------------
    public void shelveReturnedBook(Book book, int daysLate) {
        shelvedBooks.put(book.isbn(), book.title());
        int fine = Math.max(0, daysLate) * 5;
        Console.ok("shelved '" + book.title() + "'" + (fine > 0 ? " (fine " + fine + " UAH)" : ""));
    }

    // --- room scheduling --------------------------------------------
    public String scheduleClassroom(Classroom room, String slot) {
        String ref = "BK-" + (nextSequenceNumber++);
        roomBookings.put(ref, room.code() + "@" + slot);
        Console.ok("booking " + ref + ": " + room.code() + " at " + slot);
        return ref;
    }

    // --- facilities safety report ---------------------------------
    public void generateFireSafetyReport() {
        fireExtinguishersChecked += 42;
        Console.ok("fire-safety report: " + fireExtinguishersChecked + " extinguishers checked, "
                + roomBookings.size() + " rooms in use");
    }

    public List<String> payslips() {
        return payslips;
    }
}
