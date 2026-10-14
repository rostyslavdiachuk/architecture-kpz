package solid.dip;

import common.Console;

/** The real mailer, now an adapter implementing {@link NotificationGateway}. */
public class SmtpEmailClient implements NotificationGateway {

    public SmtpEmailClient() {
        Console.note("SmtpEmailClient: opening SMTP session to mail.chnu.edu.ua:587 ...");
    }

    @Override
    public void notify(String recipient, String subject, String body) {
        Console.step("SMTP -> " + recipient + " | " + subject + " | " + body);
    }
}
