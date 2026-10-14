package solid.isp;

import common.Console;

import java.util.List;

/**
 * End-of-term job. All it needs is "something that can submit grades", but the
 * only type available is the fat {@link UniversityMember}, so it accepts the
 * whole campus and hopes every element supports the call.
 */
public class TermClosingJob {

    public void collectGrades(List<UniversityMember> members, String courseCode) {
        for (UniversityMember m : members) {
            try {
                m.submitGrades(courseCode);
            } catch (UnsupportedOperationException e) {
                Console.fail("submitGrades on " + m.fullName() + ": " + e.getMessage());
            }
        }
    }
}
