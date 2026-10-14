package grasp.polymorphism;

/**
 * No type-switch anywhere: it delegates to the {@link Channel} the notification
 * carries. It never learns about a new channel.
 */
public class NotificationCenter {

    public void send(Notification n) {
        n.channel().deliver(n.recipient(), n.text());
    }

    public String formatPreview(Notification n) {
        return n.channel().previewLabel(n.recipient(), n.text());
    }

    public double estimateCostUah(Notification n) {
        return n.channel().costUah(n.text());
    }
}
