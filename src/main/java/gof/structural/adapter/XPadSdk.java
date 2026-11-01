package gof.structural.adapter;

/**
 * The gamepad vendor's library. We only have the compiled jar, so this class
 * cannot be changed, and it knows nothing about {@link InputDevice}.
 *
 * <p>The stick reports a raw value from -32768 to 32767, and the buttons come
 * as a bit mask.
 */
public final class XPadSdk {

    public static final int BUTTON_A = 0x1000;
    public static final short STICK_FULL_RIGHT = Short.MAX_VALUE;

    private short leftStickX;
    private int buttonMask;

    public short readLeftStickX() {
        return leftStickX;
    }

    public int readButtonMask() {
        return buttonMask;
    }

    // pretend: the player touches the hardware
    public void pushLeftStick(short value) {
        this.leftStickX = value;
    }

    public void press(int button) {
        this.buttonMask |= button;
    }
}
