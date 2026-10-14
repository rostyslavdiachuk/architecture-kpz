package solid.lsp;

import common.Console;

import java.util.List;

public class LspDemo {

    public static void main(String[] args) {
        Console.header("SOLID / LSP -- a subclass that breaks the CourseSection contract");

        CourseSection onCampus = new CourseSection("CS201-A", 2);
        CourseSection online = new SelfPacedOnlineSection("CS201-ONLINE");
        CourseSection waitlisted = new WaitlistedSection("CS201-B", 1);

        Student ivan = new Student("S-10", "Ivan Tkachuk");
        Student maria = new Student("S-11", "Maria Shevchuk");
        Student yuriy = new Student("S-12", "Yuriy Melnyk");
        Student anna = new Student("S-13", "Anna Lysenko");

        Console.step("Nightly RegistrationBatch processes every request the same way");
        new RegistrationBatch().run(List.of(
                new RegistrationBatch.Request(onCampus, ivan),
                new RegistrationBatch.Request(onCampus, maria),
                new RegistrationBatch.Request(online, yuriy),       // subclass throws
                new RegistrationBatch.Request(waitlisted, anna),    // fills the 1 seat
                new RegistrationBatch.Request(waitlisted, ivan)     // silently dropped
        ));

        Console.header("Why it broke");
        Console.fail("SelfPacedOnlineSection.enroll() throws UnsupportedOperationException "
                + "-- it is not substitutable for CourseSection.");
        Console.fail("WaitlistedSection.enroll() returns without enrolling and without "
                + "throwing -- it weakens the postcondition.");
        Console.note("Refactor task: define an 'Enrollable' abstraction that only the "
                + "sections that really can enroll implement; move 'room' off the base.");
    }
}
