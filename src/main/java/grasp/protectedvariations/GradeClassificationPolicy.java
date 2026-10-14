package grasp.protectedvariations;

/**
 * The stable interface in front of an unstable rule. Consumers depend on this;
 * the ministry's revisions are absorbed by swapping the implementation.
 */
public interface GradeClassificationPolicy {

    String classify(int finalScore);

    String latinHonors(double gpa);
}
