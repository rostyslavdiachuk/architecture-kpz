package grasp.polymorphism;

/**
 * A delivery channel now answers every channel-specific question itself. Adding
 * a channel is adding a class; {@link NotificationCenter} never changes.
 */
public interface Channel {

    void deliver(String recipient, String text);

    String previewLabel(String recipient, String text);

    double costUah(String text);
}
