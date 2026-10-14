package grasp.creator;

public class Enrollment {

    private final Student student;
    private final Course course;
    private final Semester semester;

    public Enrollment(Student student, Course course, Semester semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public Student student() {
        return student;
    }

    public Course course() {
        return course;
    }

    public Semester semester() {
        return semester;
    }
}
