package solid.lsp;

import java.util.List;

import common.Console;

public class LspDemo {

    public static void main(String[] args) {
        Console.header("SOLID / LSP -- every section honours the Enrollable contract");

        ScheduledSection onCampus = new ScheduledSection("CS201-A", 2);
        onCampus.scheduleRoom("A-201");
        SelfPacedOnlineSection online = new SelfPacedOnlineSection("CS201-ONLINE");
        WaitlistedSection waitlisted = new WaitlistedSection("CS201-B", 1);

        Student ivan = new Student("S-10", "Ivan Tkachuk");
        Student maria = new Student("S-11", "Maria Shevchuk");
        Student yuriy = new Student("S-12", "Yuriy Melnyk");
        Student anna = new Student("S-13", "Anna Lysenko");

        Console.step("Nightly RegistrationBatch processes every request through Enrollable");
        new RegistrationBatch().run(List.of(
                new RegistrationBatch.Request(onCampus, ivan),
                new RegistrationBatch.Request(onCampus, maria),
                new RegistrationBatch.Request(online, yuriy),
                new RegistrationBatch.Request(waitlisted, anna),
                new RegistrationBatch.Request(waitlisted, ivan)));

        Console.step("Ivan takes the open waitlist slot explicitly");
        waitlisted.joinWaitlist(ivan);

        Console.header("Result");
        Console.ok("online section rosters: " + online.roster().size()
                + " | on-campus: " + onCampus.roster().size()
                + " | waitlisted roster: " + waitlisted.roster().size()
                + ", waitlist: " + waitlisted.waitlist().size());
        Console.ok("The batch cannot tell which concrete section it holds -- substitution "
                + "holds, and 'room' lives only on ScheduledSection.");
    }
}
