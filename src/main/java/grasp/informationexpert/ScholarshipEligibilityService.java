package grasp.informationexpert;

/**
 * A second place that computes the GPA by reaching through the same getters --
 * written by a different team, at a different time, with a different rule: this
 * one averages the courses <b>unweighted</b> (ignores credits).
 *
 * <p>Same student, different number. Nobody notices until a borderline case.
 */
public class ScholarshipEligibilityService {

    private static final double CUTOFF = 3.60;

    public boolean isEligible(Student student) {
        return gpa(student) >= CUTOFF;
    }

    public double gpa(Student student) {
        double sum = 0.0;
        int n = 0;
        for (Enrollment e : student.enrollments()) {
            sum += e.grade().points();
            n++;
        }
        if (n == 0) {
            return 0.0;
        }
        return Math.round((sum / n) * 100.0) / 100.0;
    }
}
