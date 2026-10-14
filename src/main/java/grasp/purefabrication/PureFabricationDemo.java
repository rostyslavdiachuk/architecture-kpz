package grasp.purefabrication;

import common.Console;

public class PureFabricationDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Pure Fabrication -- persistence moved into a repository");

        Console.step("Create a Student and check a pure domain rule -- nothing else happens");
        Student s = new Student(7001, "Larysa Chub", "larysa@chnu.edu.ua",
                "BSc Software Engineering", 3.9);
        Console.ok("isEligibleForDeansList() = " + s.isEligibleForDeansList()
                + "  (no connection opened, no import java.sql)");

        Console.step("Persist + welcome, via the fabricated collaborators");
        StudentRepository repository = new StudentRepository();
        WelcomeMailer mailer = new WelcomeMailer();
        repository.save(s);
        mailer.sendWelcome(s);

        Console.ok("Student has one reason to change (the domain); StudentRepository owns "
                + "storage, WelcomeMailer owns e-mail.");
    }
}
