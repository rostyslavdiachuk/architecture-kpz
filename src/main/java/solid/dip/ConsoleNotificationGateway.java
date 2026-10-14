package solid.dip;

import common.Console;

/** Prints instead of sending. */
public class ConsoleNotificationGateway implements NotificationGateway {

    @Override
    public void notify(String recipient, String subject, String body) {
        Console.step("notify -> " + recipient + " | " + subject + " | " + body);
    }
}
