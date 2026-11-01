package gof.structural.adapter;

/**
 * Adapter smell: the game logic knows about the vendor's API.
 *
 * <p>The controller holds two kinds of devices and branches on which one it got.
 * The stick's raw value must be converted to -1 / 0 / +1, and that detail
 * is now the job of the code that moves the hero. Every new gamepad adds a branch.
 */
public class HeroController {

    private final Keyboard keyboard;
    private final XPadSdk xpad;

    private int position;
    private boolean attacking;

    public HeroController(Keyboard keyboard) {
        this.keyboard = keyboard;
        this.xpad = null;
    }

    public HeroController(XPadSdk xpad) {
        this.keyboard = null;
        this.xpad = xpad;
    }

    public void tick() {
        if (keyboard != null) {
            position += keyboard.moveX();
            attacking = keyboard.attackPressed();
        } else {
            position += xpad.readLeftStickX(); // the stick moves the hero, right?
            attacking = (xpad.readButtonMask() & XPadSdk.BUTTON_A) != 0;
        }
    }

    public int position() {
        return position;
    }

    public boolean attacking() {
        return attacking;
    }
}
