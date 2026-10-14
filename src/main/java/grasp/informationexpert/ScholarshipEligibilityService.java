package grasp.informationexpert;

/** Same source of truth as everyone else: {@code student.transcript().gpa()}. */
public class ScholarshipEligibilityService {

    private static final double CUTOFF = 3.60;

    public boolean isEligible(Student student) {
        return gpa(student) >= CUTOFF;
    }

    public double gpa(Student student) {
        return student.transcript().gpa();
    }
}
