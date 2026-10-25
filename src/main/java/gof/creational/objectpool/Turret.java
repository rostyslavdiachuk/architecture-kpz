package gof.creational.objectpool;

/**
 * Object Pool smell: brand-new {@link Bolt}s for every volley.
 *
 * <p>A volley needs only 5 bolts, and they are done as soon as they hit. But
 * every volley pays the full creation cost again, and the old bolts are thrown away.
 */
public class Turret {

    public static final int BOLTS_PER_VOLLEY = 5;

    public void fireVolley(String target) {
        for (int i = 0; i < BOLTS_PER_VOLLEY; i++) {
            Bolt bolt = new Bolt(target);
            // the bolt hits the target and is thrown away
        }
    }
}
