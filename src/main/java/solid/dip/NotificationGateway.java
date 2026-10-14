package solid.dip;

/** Abstraction owned by the policy. SMTP, SMS, or a console can back it. */
public interface NotificationGateway {

    void notify(String recipient, String subject, String body);
}
