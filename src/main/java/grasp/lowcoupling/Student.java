package grasp.lowcoupling;

import java.util.List;

public record Student(String id, String fullName, List<String> passedCourseCodes,
                      int earnedCredits, double gpa) {
}
