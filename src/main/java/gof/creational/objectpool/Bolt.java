package gof.creational.objectpool;

/** A crossbow bolt flying across the screen. */
public class Bolt {

    /** Object header + position + velocity + trail-particle buffer. */
    public static final int SIZE_BYTES = 512;

    private int x;
    private final int speed;

    public Bolt(int x, int speed) {
        this.x = x;
        this.speed = speed;
    }

    public void move() {
        x += speed;
    }

    public boolean hitWall(int wallX) {
        return x >= wallX;
    }
}
