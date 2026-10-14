package grasp.purefabrication;

import common.Console;

/** Another pure fabrication: the outbound-e-mail concern, kept out of the entity. */
public class WelcomeMailer {

    public void sendWelcome(Student student) {
        Console.step("SMTP -> " + student.email() + " | Welcome to " + student.programme()
                + ", " + student.fullName() + "!");
    }
}
