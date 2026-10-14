package solid.isp;

import java.util.List;

import solid.isp.Roles.Teacher;

/**
 * It needs "things that can submit grades", so that is exactly the type it
 * asks for. No {@code try/catch}, no members that cannot do the job.
 */
public class TermClosingJob {

    public void collectGrades(List<Teacher> teachers, String courseCode) {
        for (Teacher t : teachers) {
            t.submitGrades(courseCode);
        }
    }
}
