package gof.creational.singleton;

import common.Console;

/**
 * Singleton: the player's options exist exactly once per game.
 *
 * <p>The private constructor makes a second instance impossible. The nested
 * {@code Holder} class is initialized by the JVM only on the first call to
 * {@link #getInstance()}, so creation is both lazy and thread-safe without any
 * locking.
 */
public final class GameSettings {

    private static int loadCount;

    private Difficulty difficulty;
    private int musicVolume;

    private GameSettings() {
        loadCount++;
        Console.note("parsing settings.cfg (load #" + loadCount + ")");
        this.difficulty = Difficulty.NORMAL;
        this.musicVolume = 80;
    }

    private static final class Holder {
        private static final GameSettings INSTANCE = new GameSettings();
    }

    public static GameSettings getInstance() {
        return Holder.INSTANCE;
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
