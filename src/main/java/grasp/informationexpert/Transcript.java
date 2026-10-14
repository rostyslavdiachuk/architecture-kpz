package grasp.informationexpert;

import java.util.List;

/**
 * Information expert for the GPA: it owns the list of enrollments, so it owns
 * the one formula that turns them into a grade-point average.
 */
public class Transcript {

    private final List<Enrollment> enrollments;

    public Transcript(List<Enrollment> enrollments) {
        this.enrollments = List.copyOf(enrollments);
    }

    public double gpa() {
        double qualityPoints = 0.0;
        int credits = 0;
        for (Enrollment e : enrollments) {
            qualityPoints += e.qualityPoints();
            credits += e.creditsAttempted();
        }
        if (credits == 0) {
            return 0.0;
        }
        return Math.round((qualityPoints / credits) * 100.0) / 100.0;
    }

    public List<Enrollment> enrollments() {
        return enrollments;
    }
}
