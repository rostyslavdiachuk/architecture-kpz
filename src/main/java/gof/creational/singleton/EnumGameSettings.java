package gof.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Enum Singleton: the JVM creates {@code INSTANCE} exactly once, during class
 * initialization, which is thread-safe by specification.
 *
 * <p>It is also the only variant that reflection ({@code setAccessible} on the
 * constructor) and serialization cannot break. The one limitation: an enum
 * cannot extend another class.
 */
public enum EnumGameSettings {
    INSTANCE;

    private int musicVolume = 80;

    EnumGameSettings() {
        Counter.CREATED.incrementAndGet();
        SettingsFile.parse();
    }

    public static int instancesCreated() {
        return Counter.CREATED.get();
    }

    public int musicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    /** Enum constants are built before the enum's own static fields, so the counter lives here. */
    private static final class Counter {
        private static final AtomicInteger CREATED = new AtomicInteger();
    }
}
