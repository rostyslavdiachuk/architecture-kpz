package gof.creational.objectpool;

/**
 * A crossbow bolt. Reused through {@link BoltPool}.
 *
 * <p>Creating one is expensive: the constructor loads the bolt's sprite and
 * its fire-trail effect (~5 ms). Reusing one costs nothing but a {@link #reset}.
 */
public class Bolt {

    public static final int CREATION_COST_MS = 5;

    private static int created;

    private String target;

    public Bolt() {
        created++; // pretend: load sprite + fire-trail effect
    }

    public static int created() {
        return created;
    }

    /** Sets every field again: a bolt from the pool still remembers its last target. */
    public void reset(String target) {
        this.target = target;
    }

    public String target() {
        return target;
    }
}
