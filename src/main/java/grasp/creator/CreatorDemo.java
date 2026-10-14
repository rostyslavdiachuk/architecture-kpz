package grasp.creator;

import common.Console;

public class CreatorDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Creator -- the Course creates its own Enrollments");

        Semester spring = new Semester("2026-Spring");
        Course course = new Course("CS305", 2); // 2 seats
        EnrollmentManager manager = new EnrollmentManager();

        manager.enroll(new Student("S-31", "Bohdan Rudenko"), course, spring);
        manager.enroll(new Student("S-32", "Nazar Koval"), course, spring);
        try {
            manager.enroll(new Student("S-33", "Solomiya Hnatiuk"), course, spring);
        } catch (IllegalStateException e) {
            Console.warn("third enrollment refused: " + e.getMessage());
        }

        Console.step("State after 3 attempts on a 2-seat course");
        Console.note("manager.allEnrollments().size() = " + manager.allEnrollments().size());
        Console.note("course.roster().size()          = " + course.roster().size());
        Console.ok("course.remainingSeats() = " + course.remainingSeats()
                + "  -- roster and index agree, capacity is enforced at the source");
    }
}
