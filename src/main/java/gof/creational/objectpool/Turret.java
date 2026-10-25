package gof.creational.objectpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Object Pool smell: a brand-new {@link Bolt} for every shot.
 *
 * <p>Only a handful of bolts are ever in the air, but every shot pays the full
 * creation cost again, and every bolt that hits the wall is thrown away.
 */
public class Turret {

    private static final int WALL_X = 100;
    private static final int BOLT_SPEED = 10;

    private final List<Bolt> flying = new ArrayList<>();
    private int maxInAir;

    public void fire() {
        flying.add(new Bolt(0, BOLT_SPEED));
        maxInAir = Math.max(maxInAir, flying.size());
    }

    public void update() {
        Iterator<Bolt> it = flying.iterator();
        while (it.hasNext()) {
            Bolt bolt = it.next();
            bolt.move();
            if (bolt.hitWall(WALL_X)) {
                it.remove(); // thrown away
            }
        }
    }

    public int maxInAir() {
        return maxInAir;
    }
}
