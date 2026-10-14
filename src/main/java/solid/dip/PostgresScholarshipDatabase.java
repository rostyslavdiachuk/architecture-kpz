package solid.dip;

import java.util.ArrayList;
import java.util.List;

import common.Console;

/**
 * The real data source, now just an <em>adapter</em> that implements
 * {@link ScholarshipRepository}. It depends on the abstraction too; the policy
 * never mentions it. Not used by the demo -- swapped in only in production.
 */
public class PostgresScholarshipDatabase implements ScholarshipRepository {

    private final List<ScholarshipApplication> rows = new ArrayList<>();

    public PostgresScholarshipDatabase() {
        Console.note("PostgresScholarshipDatabase: connecting to "
                + "jdbc:postgresql://db.chnu.edu.ua:5432/scholarships ...");
        rows.add(new ScholarshipApplication("S-01", "olena@chnu.edu.ua", 3.9, 6000));
        rows.add(new ScholarshipApplication("S-02", "petro@chnu.edu.ua", 3.2, 4200));
        rows.add(new ScholarshipApplication("S-03", "iryna@chnu.edu.ua", 3.7, 21000));
    }

    @Override
    public List<ScholarshipApplication> findPending() {
        return rows;
    }

    @Override
    public void update(ScholarshipApplication application) {
        Console.note("UPDATE scholarship_applications SET awarded=true WHERE student_id='"
                + application.studentId() + "'");
    }
}
