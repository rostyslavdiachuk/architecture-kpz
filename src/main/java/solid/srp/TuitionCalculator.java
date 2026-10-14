package solid.srp;

/** One responsibility: how much a course costs a given student. */
public class TuitionCalculator {

    private static final int SENIOR_STUDENT_COURSE_COUNT = 10;
    private static final double SENIOR_DISCOUNT = 0.9;

    public int tuitionFor(Student student, Course course) {
        int base = course.credits() * course.pricePerCredit();
        if (student.completedCourseCodes().size() >= SENIOR_STUDENT_COURSE_COUNT) {
            return (int) Math.round(base * SENIOR_DISCOUNT);
        }
        return base;
    }
}
