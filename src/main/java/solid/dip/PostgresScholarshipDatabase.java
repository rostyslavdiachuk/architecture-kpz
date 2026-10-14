package solid.dip;

import common.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * A stand-in for a real JDBC data source. The list is fake; the point is the
 * constructor -- it "connects" as soon as it exists, so anything that creates
 * one needs a live database.
 */
public class PostgresScholarshipDatabase {

    private final List<ScholarshipApplication> rows = new ArrayList<>();

    public PostgresScholarshipDatabase() {
        Console.note("PostgresScholarshipDatabase: connecting to "
                + "jdbc:postgresql://db.chnu.edu.ua:5432/scholarships ...");
        rows.add(new ScholarshipApplication("S-01", "olena@chnu.edu.ua", 3.9, 6000));
        rows.add(new ScholarshipApplication("S-02", "petro@chnu.edu.ua", 3.2, 4200));
        rows.add(new ScholarshipApplication("S-03", "iryna@chnu.edu.ua", 3.7, 21000));
    }

    public List<ScholarshipApplication> findPendingApplications() {
        return rows;
    }

    public void update(ScholarshipApplication application) {
        Console.note("UPDATE scholarship_applications SET awarded=true WHERE student_id='"
                + application.studentId() + "'");
    }
}
