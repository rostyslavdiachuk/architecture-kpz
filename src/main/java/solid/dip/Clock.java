package solid.dip;

import java.time.LocalDate;

/** Abstraction owned by the policy, so a test can pin "today". */
public interface Clock {

    LocalDate today();
}
