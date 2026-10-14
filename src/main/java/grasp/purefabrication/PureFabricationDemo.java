package grasp.purefabrication;

import common.Console;

public class PureFabricationDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Pure Fabrication -- Student persists itself");

        Console.step("Create a Student and check a pure domain rule");
        Student s = new Student(7001, "Larysa Chub", "larysa@chnu.edu.ua",
                "BSc Software Engineering", 3.9);
        Console.note("isEligibleForDeansList() = " + s.isEligibleForDeansList());
        Console.fail("...but loading the Student class already opened a JDBC connection "
                + "(see FakeConnection above) -- the domain rule drags the database with it.");

        Console.step("Persist + welcome");
        s.saveToDatabase();
        s.emailWelcome();

        Console.header("Why this hurts");
        Console.fail("Student has 3 reasons to change: the domain model, the SQL dialect, "
                + "the e-mail wording.");
        Console.note("Refactor task: invent a StudentRepository (a 'pure fabrication' -- not "
                + "a domain concept, created to hold persistence with high cohesion and low "
                + "coupling) and a WelcomeMailer; Student becomes pure domain.");
    }
}
