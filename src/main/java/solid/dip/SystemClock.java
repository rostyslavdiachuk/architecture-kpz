package solid.dip;

import java.time.LocalDate;

/** Wall-clock date. Hard to pin down in a test when it is created internally. */
public class SystemClock {

    public LocalDate today() {
        return LocalDate.now();
    }
}
