package grasp.creator;

import common.Console;

public class CreatorDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Creator -- Enrollment built outside the Course");

        Semester spring = new Semester("2026-Spring");
        Course course = new Course("CS305", 2); // 2 seats
        EnrollmentManager manager = new EnrollmentManager();

        manager.createEnrollment(new Student("S-31", "Bohdan Rudenko"), course, spring);
        manager.createEnrollment(new Student("S-32", "Nazar Koval"), course, spring);
        manager.createEnrollment(new Student("S-33", "Solomiya Hnatiuk"), course, spring);

        Console.step("3 enrollments created for a 2-seat course");
        Console.note("manager.allEnrollments().size() = " + manager.allEnrollments().size());
        Console.note("course.roster().size()          = " + course.roster().size());
        Console.fail("course.remainingSeats() = " + course.remainingSeats()
                + "  -- the course thinks it is empty and will keep accepting students");

        Console.header("Why it broke");
        Console.fail("Creation lives in EnrollmentManager, so 'add it to the course roster' "
                + "is a separate step -- and it was skipped.");
        Console.note("Refactor task: Course.enroll(student, semester) creates the Enrollment "
                + "(Course aggregates enrollments and has the data), enforces capacity, and "
                + "adds it to its own roster.");
    }
}
