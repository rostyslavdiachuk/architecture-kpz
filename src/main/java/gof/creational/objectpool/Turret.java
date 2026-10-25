package gof.creational.objectpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Object Pool smell: a fresh {@link Bolt} for every shot, garbage for every hit.
 *
 * <p>The turret fires a few bolts every frame and each one lives for only a
 * fraction of a second. Every {@code new Bolt} goes on the heap, every bolt
 * that hits the wall becomes garbage, and sooner or later the collector stops
 * the whole game to clean up, in the middle of a fight.
 */
public class Turret {

    private static final int WALL_X = 150;
    private static final int BOLT_SPEED = 10;

    private final GameHeap heap;
    private final List<Bolt> flying = new ArrayList<>();

    public Turret(GameHeap heap) {
        this.heap = heap;
    }

    /** @return ms the game was frozen by this shot */
    public int fire() {
        int pause = heap.allocate(Bolt.SIZE_BYTES);
        flying.add(new Bolt(0, BOLT_SPEED));
        return pause;
    }

    public void update() {
        Iterator<Bolt> it = flying.iterator();
        while (it.hasNext()) {
            Bolt bolt = it.next();
            bolt.move();
            if (bolt.hitWall(WALL_X)) {
                it.remove(); // the bolt is now garbage for the GC
            }
        }
    }
}
