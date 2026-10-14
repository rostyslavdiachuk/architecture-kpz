package grasp.lowcoupling;

import java.util.List;

/**
 * Where a decision goes once it is made. The diploma renderer, the mailer and
 * the event bus all sit behind this one small role.
 */
public interface GraduationGateway {

    void announceCleared(String studentName, String programme);

    void announceBlocked(String studentName, List<String> reasons);
}
