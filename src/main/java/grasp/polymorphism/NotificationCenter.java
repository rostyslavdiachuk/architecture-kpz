package grasp.polymorphism;

import common.Console;

/**
 * GRASP / Polymorphism smell: behaviour selected by an {@code if}/{@code else}
 * chain on a type code, and the <b>same</b> chain repeated in three methods.
 * A new channel means finding and editing every chain; miss one and it fails
 * differently (dropped message / zero cost / broken preview).
 */
public class NotificationCenter {

    public void send(Notification n) {
        if (n.channel() == Channel.EMAIL) {
            Console.step("SMTP -> " + n.recipient() + " : " + n.text());
        } else if (n.channel() == Channel.SMS) {
            Console.step("SMS -> " + n.recipient() + " : " + trim(n.text(), 160));
        } else if (n.channel() == Channel.PUSH) {
            Console.step("PUSH -> device(" + n.recipient() + ") : " + n.text());
        } else if (n.channel() == Channel.TELEGRAM) {
            Console.step("Telegram Bot API -> @" + n.recipient() + " : " + n.text());
        } else {
            Console.fail("unknown channel " + n.channel() + ", message dropped");
        }
    }

    public String formatPreview(Notification n) {
        if (n.channel() == Channel.EMAIL) {
            return "Email to " + n.recipient();
        } else if (n.channel() == Channel.SMS) {
            return "SMS to " + n.recipient() + " (" + Math.min(n.text().length(), 160) + " chars)";
        } else if (n.channel() == Channel.PUSH) {
            return "Push to device " + n.recipient();
        } else if (n.channel() == Channel.TELEGRAM) {
            return "Telegram to @" + n.recipient();
        } else {
            return "???";
        }
    }

    public double estimateCostUah(Notification n) {
        if (n.channel() == Channel.EMAIL) {
            return 0.00;
        } else if (n.channel() == Channel.SMS) {
            return 0.85;
        } else if (n.channel() == Channel.PUSH) {
            return 0.01;
        } else if (n.channel() == Channel.TELEGRAM) {
            return 0.00;
        } else {
            return 0.00;
        }
    }

    private static String trim(String s, int max) {
        return s.length() <= max ? s : s.substring(0, max);
    }
}
