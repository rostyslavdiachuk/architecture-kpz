package grasp.controller;

import common.Console;
import grasp.controller.Infrastructure.AuditLog;
import grasp.controller.Infrastructure.CourseCatalog;
import grasp.controller.Infrastructure.EmailNotifier;
import grasp.controller.Infrastructure.StudentDirectory;

public class ControllerDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Controller -- one controller behind both UIs");

        StudentDirectory students = new StudentDirectory();
        students.add(new Student("S-41", "Taras Zinchenko", "taras@chnu.edu.ua"));
        students.add(new Student("S-42", "Oksana Prokopiv", "oksana@chnu.edu.ua"));

        CourseCatalog courses = new CourseCatalog();
        courses.add(new Course("CS210", 30));

        AuditLog audit = new AuditLog();
        RegistrationController controller = new RegistrationController(
                students, courses, new EmailNotifier(), audit);

        Console.step("Register S-41 through the console UI");
        new ConsoleRegistrationUI(controller).onRegisterMenuOption("S-41", "CS210");

        Console.step("Register S-42 through the REST handler");
        new RestRegistrationHandler(controller).post("S-42", "CS210");

        Console.header("Compare the two paths");
        Console.ok("Both paths e-mailed a confirmation.");
        Console.ok("Audit trail is one consistent format:");
        audit.lines().forEach(Console::note);
    }
}
