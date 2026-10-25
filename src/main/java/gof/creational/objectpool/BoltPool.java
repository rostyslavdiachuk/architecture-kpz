package gof.creational.objectpool;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Object Pool: a fixed set of bolts, created once and reused for the whole fight.
 *
 * <p>All bolts are created while the level loads. During the fight
 * {@link #acquire()} hands out a free bolt and {@link #release(Bolt)} takes it back.
 */
public class BoltPool {

    private final Deque<Bolt> free = new ArrayDeque<>();

    public BoltPool(int capacity) {
        for (int i = 0; i < capacity; i++) {
            free.push(new Bolt());
        }
    }

    /** @return a free bolt, or {@code null} when every bolt is in use */
    public Bolt acquire() {
        return free.poll();
    }

    public void release(Bolt bolt) {
        free.push(bolt);
    }
}
