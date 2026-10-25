package gof.creational.singleton;

import common.Console;

public class SingletonDemo {

    public static void main(String[] args) {
        Console.header("GoF / Singleton -- one GameSettings shared by every subsystem");

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
            Console.ok("the player muted the music -- the dungeon theme plays at volume 0");
        }
        if (spawner.difficultyInUse() != Difficulty.HARD) {
            Console.fail("the player picked HARD, but the spawner uses " + spawner.difficultyInUse());
        } else {
            Console.ok("the player picked HARD -- goblin spawned with " + goblinHp + " HP");
        }
        Console.ok("settings.cfg was parsed " + GameSettings.loadCount()
                + " time -- the menu, audio and spawner all hold the same instance");
        Console.note("`new GameSettings()` no longer compiles: the private constructor guarantees "
                + "there is only one.");
    }
}
