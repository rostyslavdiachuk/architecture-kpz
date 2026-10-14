package grasp.lowcoupling;

import java.util.List;

import common.Console;

/**
 * GRASP / Low Coupling applied.
 *
 * <p>Two collaborators, both abstractions, both injected. No {@code new} of any
 * infrastructure. The degree rules are not here either -- each
 * {@link DegreeRequirement} checks itself, so this class only sequences the
 * work.
 */
public class DegreeAuditService {

    private final DegreeRequirements requirements;
    private final GraduationGateway gateway;

    public DegreeAuditService(DegreeRequirements requirements, GraduationGateway gateway) {
        this.requirements = requirements;
        this.gateway = gateway;
    }

    public boolean audit(StudentAcademicRecord record, String programme) {
        List<String> unmet = requirements.unmetReasons(record);
        if (unmet.isEmpty()) {
            gateway.announceCleared(record.studentName(), programme);
            Console.ok(record.studentName() + " cleared for graduation");
            return true;
        }
        gateway.announceBlocked(record.studentName(), unmet);
        Console.fail(record.studentName() + " NOT cleared: " + unmet);
        return false;
    }
}
