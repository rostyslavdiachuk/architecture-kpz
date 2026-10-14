package grasp.highcohesion;

/** Small records shared by the demo. */
public final class Domain {

    private Domain() {
    }

    public record Student(String id, String fullName) {
    }

    public record Lecturer(String id, String fullName, int monthlySalaryUah) {
    }

    public record Classroom(String code, int seats) {
    }

    public record Book(String isbn, String title) {
    }
}
