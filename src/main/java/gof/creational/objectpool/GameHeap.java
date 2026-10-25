package gof.creational.objectpool;

/**
 * A deterministic stand-in for the JVM heap and garbage collector.
 *
 * <p>Every allocation adds to the "young generation". When it fills up, the
 * collector stops the game for {@link #GC_PAUSE_MS} ms.
 */
public class GameHeap {

    public static final int GC_PAUSE_MS = 40;
    private static final int YOUNG_GEN_BYTES = 64 * 1024;

    private int allocations;
    private int bytesSinceLastGc;
    private int gcPauses;

    /** @return how long the game was frozen by this allocation, in ms */
    public int allocate(int bytes) {
        allocations++;
        bytesSinceLastGc += bytes;
        if (bytesSinceLastGc >= YOUNG_GEN_BYTES) {
            bytesSinceLastGc = 0;
            gcPauses++;
            return GC_PAUSE_MS;
        }
        return 0;
    }

    public int allocations() {
        return allocations;
    }

    public int gcPauses() {
        return gcPauses;
    }
}
