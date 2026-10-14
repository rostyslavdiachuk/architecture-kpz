package grasp.informationexpert;

/**
 * Anemic: it holds a course and a grade but exposes only getters. The class
 * that has the data does nothing with it, so every caller recomputes
 * "quality points = grade points x credits" for itself -- and they disagree.
 */
public class Enrollment {

    private final Course course;
    private final Grade grade;

    public Enrollment(Course course, Grade grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course course() {
        return course;
    }

    public Grade grade() {
        return grade;
    }
}
