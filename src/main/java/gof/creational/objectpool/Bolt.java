package gof.creational.objectpool;

/**
 * A crossbow bolt.
 *
 * <p>Creating one is expensive: the constructor loads the bolt's sprite and
 * its fire-trail effect (~5 ms).
 */
public class Bolt {

    public static final int CREATION_COST_MS = 5;

    private static int created;

    private final String target;

    public Bolt(String target) {
        created++; // pretend: load sprite + fire-trail effect
        this.target = target;
    }

    public static int created() {
        return created;
    }

    public String target() {
        return target;
    }
}
