package gof.creational.objectpool;

import java.util.ArrayList;
import java.util.List;

/**
 * Fires volleys with bolts taken from a {@link BoltPool} and puts them back
 * after they hit. No bolt is created during the fight.
 */
public class Turret {

    public static final int BOLTS_PER_VOLLEY = 5;

    private final BoltPool pool;

    public Turret(BoltPool pool) {
        this.pool = pool;
    }

    public void fireVolley(String target) {
        List<Bolt> volley = new ArrayList<>();
        for (int i = 0; i < BOLTS_PER_VOLLEY; i++) {
            Bolt bolt = pool.acquire();
            if (bolt == null) {
                break; // pool is empty: fire fewer bolts
            }
            bolt.reset(target);
            volley.add(bolt);
        }
        // the bolts hit the target...
        for (Bolt bolt : volley) {
            pool.release(bolt); // ...and go back to the pool for the next volley
        }
    }
}
