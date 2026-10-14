package grasp.controller;

import common.Console;
import grasp.controller.Infrastructure.AuditLog;
import grasp.controller.Infrastructure.CourseCatalog;
import grasp.controller.Infrastructure.EmailNotifier;
import grasp.controller.Infrastructure.StudentDirectory;

public class ControllerDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Controller -- two UIs each orchestrate registration");

        StudentDirectory students = new StudentDirectory();
        students.add(new Student("S-41", "Taras Zinchenko", "taras@chnu.edu.ua"));
        students.add(new Student("S-42", "Oksana Prokopiv", "oksana@chnu.edu.ua"));

        CourseCatalog courses = new CourseCatalog();
        courses.add(new Course("CS210", 30));

        EmailNotifier email = new EmailNotifier();
        AuditLog audit = new AuditLog();

        Console.step("Register S-41 through the console UI");
        new ConsoleRegistrationUI(students, courses, email, audit).onRegisterMenuOption("S-41", "CS210");

        Console.step("Register S-42 through the REST handler");
        new RestRegistrationHandler(students, courses, email, audit).post("S-42", "CS210");

        Console.header("Compare the two paths");
        Console.fail("Only the console path sent a confirmation e-mail.");
        Console.fail("Audit trail has two incompatible formats:");
        audit.lines().forEach(Console::note);
        Console.note("Refactor task: one RegistrationController.registerStudentForCourse("
                + "command) that both UIs call; the UI only collects input and shows the "
                + "result.");
    }
}
