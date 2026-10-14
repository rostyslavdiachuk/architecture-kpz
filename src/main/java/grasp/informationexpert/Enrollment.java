package grasp.informationexpert;

/**
 * Information expert for one line of the record: it holds the grade and the
 * course, so it is the right place to turn them into quality points.
 */
public class Enrollment {

    private final Course course;
    private final Grade grade;

    public Enrollment(Course course, Grade grade) {
        this.course = course;
        this.grade = grade;
    }

    public int creditsAttempted() {
        return course.credits();
    }

    /** GPA points earned for this course, weighted by its credits. */
    public double qualityPoints() {
        return grade.points() * course.credits();
    }

    public Course course() {
        return course;
    }

    public Grade grade() {
        return grade;
    }
}
