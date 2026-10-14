package solid.dip;

import common.Console;

/** Stand-in for a real mail client. Again, the constructor is the problem. */
public class SmtpEmailClient {

    public SmtpEmailClient() {
        Console.note("SmtpEmailClient: opening SMTP session to mail.chnu.edu.ua:587 ...");
    }

    public void send(String to, String subject, String body) {
        Console.step("SMTP -> " + to + " | " + subject + " | " + body);
    }
}
