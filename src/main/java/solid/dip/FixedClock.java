package solid.dip;

import java.time.LocalDate;

/** A clock that always returns the same date, so output is reproducible. */
public class FixedClock implements Clock {

    private final LocalDate date;

    public FixedClock(LocalDate date) {
        this.date = date;
    }

    @Override
    public LocalDate today() {
        return date;
    }
}
