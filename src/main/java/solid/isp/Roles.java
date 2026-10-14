package solid.isp;

/**
 * The fat {@code UniversityMember} split into role interfaces. A type implements
 * only the roles it can actually fulfil, and a client depends only on the role
 * it needs.
 */
public final class Roles {

    private Roles() {
    }

    public interface Teacher {
        String fullName();

        void teachCourse(String courseCode);

        void submitGrades(String courseCode);

        void holdOfficeHours();
    }

    public interface ThesisSupervisor {
        void superviseThesis(String studentId);
    }

    public interface Learner {
        String fullName();

        void enrollInCourse(String courseCode);

        void payTuition(int amountUah);
    }

    public interface TranscriptRequester {
        String fullName();

        void requestTranscript();
    }

    public interface BudgetApprover {
        void approveDepartmentBudget(int amountUah);
    }
}
