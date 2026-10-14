package solid.dip;

import java.time.LocalDate;

/** The real clock, now an adapter implementing {@link Clock}. */
public class SystemClock implements Clock {

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}
