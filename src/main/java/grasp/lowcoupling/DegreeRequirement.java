package grasp.lowcoupling;

import java.util.Optional;

/** One graduation rule that checks itself against a record. */
public interface DegreeRequirement {

    /** @return why the record fails this requirement, or empty if it passes. */
    Optional<String> unmetReason(StudentAcademicRecord record);
}
