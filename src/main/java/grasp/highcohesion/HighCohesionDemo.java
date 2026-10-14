package grasp.highcohesion;

import java.util.List;

import common.Console;
import grasp.highcohesion.Domain.Book;
import grasp.highcohesion.Domain.Classroom;
import grasp.highcohesion.Domain.Lecturer;

public class HighCohesionDemo {

    public static void main(String[] args) {
        Console.header("GRASP / High Cohesion -- one cohesive service per job");

        RegistrationService registration = new RegistrationService();
        PayrollService payroll = new PayrollService();
        LibraryService library = new LibraryService();
        ClassroomScheduler scheduler = new ClassroomScheduler();
        FacilitiesReportService facilities = new FacilitiesReportService(scheduler);

        registration.registerStudent("Roman Sydir");
        registration.registerStudent("Yana Lutsiv");
        scheduler.scheduleClassroom(new Classroom("A-201", 60), "Mon 08:30");
        payroll.runPayroll(List.of(
                new Lecturer("L-1", "Dr. Mykola Chorny", 42000),
                new Lecturer("L-2", "Dr. Iryna Bila", 45000)));
        library.shelveReturnedBook(new Book("978-1", "Design Patterns"), 3);
        facilities.generateFireSafetyReport();

        Console.header("Result");
        Console.ok("Payslips numbered independently: " + payroll.payslips()
                + " -- students still start at STU-0001, bookings at BK-0001.");
        Console.ok("Each service owns only its own state; a change to one job cannot move "
                + "the numbering of another.");
    }
}
