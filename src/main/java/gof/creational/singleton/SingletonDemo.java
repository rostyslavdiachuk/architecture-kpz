package gof.creational.singleton;

import common.Console;

public class SingletonDemo {

    public static void main(String[] args) {
        Console.header("GoF / Singleton -- every subsystem builds its own GameSettings");

        Console.step("Booting Dungeon Raid: options menu, audio engine, enemy spawner");
        OptionsMenu options = new OptionsMenu();
        AudioEngine audio = new AudioEngine();
        EnemySpawner spawner = new EnemySpawner();

        Console.header("Player opens the options menu");
        options.chooseDifficulty(Difficulty.HARD);
        options.muteMusic();

        Console.header("Player enters the first dungeon");
        int volume = audio.playDungeonTheme();
        int goblinHp = spawner.spawnGoblin();

        if (volume > 0) {
            Console.fail("the player muted the music, but the dungeon theme plays at volume " + volume);
        } else {
            Console.ok("music is muted");
        }
        if (spawner.difficultyInUse() != Difficulty.HARD) {
            Console.fail("the player picked HARD, but the spawner uses " + spawner.difficultyInUse()
                    + " -- goblin spawned with " + goblinHp + " HP instead of 60");
        } else {
            Console.ok("goblin spawned with " + goblinHp + " HP on HARD");
        }
        Console.warn("settings.cfg was parsed " + GameSettings.loadCount()
                + " times for one game -- there are " + GameSettings.loadCount() + " 'truths'");
        Console.note("Refactor task: make GameSettings a Singleton -- private constructor and one "
                + "shared instance that every subsystem gets through getInstance().");
    }
}
