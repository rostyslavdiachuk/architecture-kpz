package grasp.polymorphism;

import common.Console;

import java.util.List;

public class PolymorphismDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Polymorphism -- one if/else type-switch, repeated 3x");

        NotificationCenter center = new NotificationCenter();

        List<Notification> supported = List.of(
                new Notification(Channel.EMAIL, "olena@chnu.edu.ua", "Exam CS201 moved to room A-201"),
                new Notification(Channel.SMS, "+380671234567", "Exam CS201 moved to A-201"),
                new Notification(Channel.PUSH, "device-88", "Exam CS201 moved"),
                new Notification(Channel.TELEGRAM, "olena_k", "Exam CS201 moved to A-201"));

        for (Notification n : supported) {
            center.send(n);
            Console.note("preview: " + center.formatPreview(n) + " | cost "
                    + center.estimateCostUah(n) + " UAH");
        }

        Console.header("Registrar: send exam alerts over Viber too");
        Notification viber = new Notification(Channel.VIBER, "olena_k", "Exam CS201 moved to A-201");
        center.send(viber);
        Console.fail("preview: " + center.formatPreview(viber) + " | cost "
                + center.estimateCostUah(viber) + " UAH  -- fell through every else branch");

        Console.note("Refactor task: a Channel interface with deliver(), previewLabel(), "
                + "cost(); one class per channel; ViberChannel added without touching "
                + "NotificationCenter.");
    }
}
