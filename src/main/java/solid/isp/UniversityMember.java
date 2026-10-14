package solid.isp;

/**
 * ISP smell: one "everyone at the university" interface.
 *
 * <p>No real member can implement all of this. {@link Student} throws from the
 * teaching / budget / supervision methods; {@link Lecturer} throws from the
 * tuition / enrollment / transcript methods. Implementers are forced to depend
 * on methods they do not use.
 */
public interface UniversityMember {

    String fullName();

    // --- teaching staff concerns ---
    void teachCourse(String courseCode);

    void submitGrades(String courseCode);

    void holdOfficeHours();

    void superviseThesis(String studentId);

    // --- student concerns ---
    void enrollInCourse(String courseCode);

    void payTuition(int amountUah);

    void requestTranscript();

    // --- department head concerns ---
    void approveDepartmentBudget(int amountUah);
}
