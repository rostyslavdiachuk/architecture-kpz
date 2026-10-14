package grasp.highcohesion;

import common.Console;
import grasp.highcohesion.Domain.Book;
import grasp.highcohesion.Domain.Classroom;
import grasp.highcohesion.Domain.Lecturer;

import java.util.List;

public class HighCohesionDemo {

    public static void main(String[] args) {
        Console.header("GRASP / High Cohesion -- UniversityAdminFacade does everything");

        UniversityAdminFacade admin = new UniversityAdminFacade();

        admin.registerStudent("Roman Sydir");
        admin.registerStudent("Yana Lutsiv");
        admin.scheduleClassroom(new Classroom("A-201", 60), "Mon 08:30");
        admin.runPayroll(List.of(
                new Lecturer("L-1", "Dr. Mykola Chorny", 42000),
                new Lecturer("L-2", "Dr. Iryna Bila", 45000)));
        admin.shelveReturnedBook(new Book("978-1", "Design Patterns"), 3);
        admin.generateFireSafetyReport();

        Console.header("Why this hurts");
        Console.note("Fields in one class: student map, payslip list, shelved books, room "
                + "bookings, extinguisher count -- no method uses more than one of them.");
        Console.fail("nextSequenceNumber is shared by student ids, booking refs and payslip "
                + "numbers: payslips came out as " + admin.payslips()
                + " -- change the payslip numbering rule and student ids move too.");
        Console.note("Refactor task: split into RegistrationService, PayrollService, "
                + "LibraryService, ClassroomScheduler, FacilitiesReportService -- each with "
                + "only the state its own job needs.");
    }
}
