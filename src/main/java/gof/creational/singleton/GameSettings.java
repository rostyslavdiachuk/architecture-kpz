package gof.creational.singleton;

import common.Console;

/**
 * Singleton smell: a "one per game" object that anybody can {@code new}.
 *
 * <p>There is exactly one settings file and the player expects exactly one set
 * of options, but the public constructor lets every subsystem create its own
 * copy. Each copy parses {@code settings.cfg} again and then lives its own life:
 * a change made through one copy is invisible to all the others.
 */
public class GameSettings {

    private static int loadCount;

    private Difficulty difficulty;
    private int musicVolume;

    public GameSettings() {
        loadCount++;
        Console.note("parsing settings.cfg (load #" + loadCount + ")");
        this.difficulty = Difficulty.NORMAL;
        this.musicVolume = 80;
    }

    public static int loadCount() {
        return loadCount;
    }

    public Difficulty difficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int musicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }
}
