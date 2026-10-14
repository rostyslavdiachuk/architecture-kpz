package grasp.polymorphism;

import common.Console;

/**
 * The new channel. Added by writing only this file -- {@link NotificationCenter}
 * and the other channels were not touched.
 */
public class ViberChannel implements Channel {

    @Override
    public void deliver(String recipient, String text) {
        Console.step("Viber REST -> " + recipient + " : " + text);
    }

    @Override
    public String previewLabel(String recipient, String text) {
        return "Viber to " + recipient;
    }

    @Override
    public double costUah(String text) {
        return 0.20;
    }
}
