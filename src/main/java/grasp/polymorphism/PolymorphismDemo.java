package grasp.polymorphism;

import java.util.List;

import common.Console;

public class PolymorphismDemo {

    public static void main(String[] args) {
        Console.header("GRASP / Polymorphism -- one Channel type per behaviour");

        NotificationCenter center = new NotificationCenter();

        List<Notification> notifications = List.of(
                new Notification(new EmailChannel(), "olena@chnu.edu.ua", "Exam CS201 moved to room A-201"),
                new Notification(new SmsChannel(), "+380671234567", "Exam CS201 moved to A-201"),
                new Notification(new PushChannel(), "device-88", "Exam CS201 moved"),
                new Notification(new TelegramChannel(), "olena_k", "Exam CS201 moved to A-201"),
                new Notification(new ViberChannel(), "olena_k", "Exam CS201 moved to A-201"));

        for (Notification n : notifications) {
            center.send(n);
            Console.note("preview: " + center.formatPreview(n) + " | cost "
                    + center.estimateCostUah(n) + " UAH");
        }

        Console.ok("Viber went out with a real preview and a real cost -- NotificationCenter "
                + "was never edited to support it.");
    }
}
