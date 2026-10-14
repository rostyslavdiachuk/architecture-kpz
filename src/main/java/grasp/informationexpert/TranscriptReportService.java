package grasp.informationexpert;

/**
 * Computes the GPA by reaching through the student's enrollments, grades and
 * courses. This logic belongs to whoever holds the enrollment list -- not here.
 *
 * <p>This copy weights each course by its credits.
 */
public class TranscriptReportService {

    public double gpa(Student student) {
        double weightedPoints = 0.0;
        int totalCredits = 0;
        for (Enrollment e : student.enrollments()) {
            weightedPoints += e.grade().points() * e.course().credits();
            totalCredits += e.course().credits();
        }
        if (totalCredits == 0) {
            return 0.0;
        }
        return Math.round((weightedPoints / totalCredits) * 100.0) / 100.0;
    }
}
