package solid.srp;

import java.util.Optional;

/** One responsibility: the academic rules for whether an enrollment is allowed. */
public class EnrollmentPolicy {

    private final int maxCreditsPerTerm;

    public EnrollmentPolicy(int maxCreditsPerTerm) {
        this.maxCreditsPerTerm = maxCreditsPerTerm;
    }

    /** @return the reason the enrollment must be refused, or empty if it is allowed. */
    public Optional<String> rejectionReason(Student student, Course course) {
        for (String prereq : course.prerequisiteCodes()) {
            if (!student.completedCourseCodes().contains(prereq)) {
                return Optional.of("missing prerequisite " + prereq);
            }
        }
        if (student.enrolledCredits() + course.credits() > maxCreditsPerTerm) {
            return Optional.of("would exceed the " + maxCreditsPerTerm + "-credit term cap");
        }
        return Optional.empty();
    }
}
