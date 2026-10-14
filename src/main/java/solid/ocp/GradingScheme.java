package solid.ocp;

/**
 * A grading scheme now answers every question about a score itself. Adding a
 * scheme means adding a class that implements this interface -- nothing else
 * changes, so the system is closed for modification and open for extension.
 */
public interface GradingScheme {

    String letterGrade(int score);

    double gpaPoints(int score);

    String label(int score);
}
