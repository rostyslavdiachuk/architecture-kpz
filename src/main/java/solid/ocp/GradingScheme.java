package solid.ocp;

/**
 * Grading schemes the university supports.
 *
 * <p>{@code THESIS} was added this term for the new master's-thesis defence.
 * Notice that adding the constant here is <em>not</em> enough: every
 * {@code switch} in {@link FinalGradeCalculator} also has to learn about it.
 */
public enum GradingScheme {
    STANDARD,
    PASS_FAIL,
    HONORS,
    ECTS,
    THESIS
}
