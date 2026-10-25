package gof.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Naive lazy Singleton: correct with one thread, broken with several.
 *
 * <p>Two threads can both see {@code instance == null} before either has
 * finished the constructor, and then each creates its own instance. The slow
 * constructor (parsing settings.cfg) makes that window wide.
 */
public final class NaiveGameSettings {

    private static final AtomicInteger created = new AtomicInteger();
    private static NaiveGameSettings instance;

    private int musicVolume = 80;

    private NaiveGameSettings() {
        created.incrementAndGet();
        SettingsFile.parse();
    }

    public static NaiveGameSettings getInstance() {
        if (instance == null) {                 // thread A and B both get here...
            instance = new NaiveGameSettings(); // ...and both create one
        }
        return instance;
    }

    public static int instancesCreated() {
        return created.get();
    }

    public int musicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }
}
