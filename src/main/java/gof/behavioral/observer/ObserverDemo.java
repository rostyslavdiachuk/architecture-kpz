package gof.behavioral.observer;

import common.Console;

public class ObserverDemo {

    public static void main(String[] args) {
        Console.header("GoF / Observer -- an enemy calls every system when it dies");

        Hud hud = new Hud();
        QuestLog quests = new QuestLog();
        AudioEngine audio = new AudioEngine();

        Console.step("The hero kills a goblin");
        new Enemy("Goblin", hud, quests, audio).die();
        report("Goblin", hud, quests, audio);

        Console.step("The hero kills the dragon");
        new Boss("Dragon", hud, quests, audio).die();
        report("Dragon", hud, quests, audio);

        Console.warn("the next feature is achievements: Enemy, Boss and both constructors have to change");
        Console.note("Refactor task: an enemy only publishes 'I died' to DeathEvents; the HUD, the "
                + "quest log and the audio engine subscribe to it. Enemy no longer knows them.");
    }

    private static void report(String target, Hud hud, QuestLog quests, AudioEngine audio) {
        Console.note("kills on the HUD: " + hud.kills() + ", last sound: " + audio.lastSound());
        if (quests.questDone(target)) {
            Console.ok("quest 'Slay the " + target + "' is done");
        } else {
            Console.fail("quest 'Slay the " + target + "' is still open -- the player killed it and "
                    + "got no reward");
        }
    }
}
