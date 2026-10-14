package solid.dip;

import java.util.List;

/** Abstraction owned by the policy. Postgres, a file, or a list can back it. */
public interface ScholarshipRepository {

    List<ScholarshipApplication> findPending();

    void update(ScholarshipApplication application);
}
