package gof.creational.objectpool;

/**
 * A crossbow bolt flying across the screen.
 *
 * <p>Creating one is expensive: the constructor loads the bolt's sprite and
 * its fire-trail effect (~5 ms).
 */
public class Bolt {

    public static final int CREATION_COST_MS = 5;

    private static int created;

    private int x;
    private final int speed;

    public Bolt(int x, int speed) {
        created++; // pretend: load sprite + fire-trail effect
        this.x = x;
        this.speed = speed;
    }

    public static int created() {
        return created;
    }

    public void move() {
        x += speed;
    }

    public boolean hitWall(int wallX) {
        return x >= wallX;
    }
}
