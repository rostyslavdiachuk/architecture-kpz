package gof.structural.adapter;

/**
 * Adapter: makes the vendor's {@link XPadSdk} look like the game's {@link InputDevice}.
 *
 * <p>All the knowledge about raw stick values and bit masks lives here. The SDK
 * stays untouched, and the game never sees it.
 */
public class XPadAdapter implements InputDevice {

    private static final int DEAD_ZONE = 8000;

    private final XPadSdk xpad;

    public XPadAdapter(XPadSdk xpad) {
        this.xpad = xpad;
    }

    @Override
    public int moveX() {
        int raw = xpad.readLeftStickX();
        if (Math.abs(raw) < DEAD_ZONE) {
            return 0;
        }
        return raw > 0 ? 1 : -1;
    }

    @Override
    public boolean attackPressed() {
        return (xpad.readButtonMask() & XPadSdk.BUTTON_A) != 0;
    }
}
