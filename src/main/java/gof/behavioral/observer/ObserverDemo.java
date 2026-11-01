package gof.behavioral.observer;

import common.Console;

public class ObserverDemo {

    public static void main(String[] args) {
        Console.header("GoF / Observer -- an enemy publishes its death, and the systems subscribe");

        Hud hud = new Hud();
        QuestLog quests = new QuestLog();
        AudioEngine audio = new AudioEngine();
        Achievements achievements = new Achievements();

        DeathEvents events = new DeathEvents();
        events.subscribe(hud);
        events.subscribe(quests);
        events.subscribe(audio);
        events.subscribe(achievements);

        Console.step("The hero kills a goblin");
        new Enemy("Goblin", events).die();
        report("Goblin", hud, quests, audio);

        Console.step("The hero kills the dragon");
        new Boss("Dragon", events).die();
        report("Dragon", hud, quests, audio);

        if (achievements.bossSlayer()) {
            Console.ok("achievement unlocked: Boss Slayer -- added without touching Enemy or Boss");
        } else {
            Console.fail("achievement 'Boss Slayer' is still locked");
        }
    }

    private static void report(String target, Hud hud, QuestLog quests, AudioEngine audio) {
        Console.note("kills on the HUD: " + hud.kills() + ", last sound: " + audio.lastSound());
        if (quests.questDone(target)) {
            Console.ok("quest 'Slay the " + target + "' is done");
        } else {
            Console.fail("quest 'Slay the " + target + "' is still open");
        }
    }
}
