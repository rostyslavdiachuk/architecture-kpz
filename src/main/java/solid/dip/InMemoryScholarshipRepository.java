package solid.dip;

import java.util.ArrayList;
import java.util.List;

import common.Console;

/** A list-backed repository -- enough to run the policy with no infrastructure. */
public class InMemoryScholarshipRepository implements ScholarshipRepository {

    private final List<ScholarshipApplication> rows = new ArrayList<>();

    public void add(ScholarshipApplication application) {
        rows.add(application);
    }

    @Override
    public List<ScholarshipApplication> findPending() {
        return rows;
    }

    @Override
    public void update(ScholarshipApplication application) {
        Console.note("repository: stored award for " + application.studentId());
    }
}
