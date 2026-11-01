package gof.structural.adapter;

/** Moves the hero. It works with any {@link InputDevice} and knows no vendor's API. */
public class HeroController {

    private final InputDevice device;

    private int position;
    private boolean attacking;

    public HeroController(InputDevice device) {
        this.device = device;
    }

    public void tick() {
        position += device.moveX();
        attacking = device.attackPressed();
    }

    public int position() {
        return position;
    }

    public boolean attacking() {
        return attacking;
    }
}
