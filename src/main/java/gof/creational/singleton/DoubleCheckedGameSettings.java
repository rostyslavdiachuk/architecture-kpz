package gof.creational.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Double-checked locking: lazy and thread-safe, and only the first calls pay
 * for the lock.
 *
 * <p>The first check skips the lock once the instance exists. The second check,
 * inside the lock, stops a thread that waited at the lock from creating a second
 * instance. {@code volatile} is required: without it the JVM may publish the
 * reference before the constructor has finished, and another thread would see a
 * half-built object.
 */
public final class DoubleCheckedGameSettings {

    private static final AtomicInteger created = new AtomicInteger();
    private static volatile DoubleCheckedGameSettings instance;

    private int musicVolume = 80;

    private DoubleCheckedGameSettings() {
        created.incrementAndGet();
        SettingsFile.parse();
    }

    public static DoubleCheckedGameSettings getInstance() {
        DoubleCheckedGameSettings result = instance;
        if (result == null) {                              // 1st check, no lock
            synchronized (DoubleCheckedGameSettings.class) {
                result = instance;
                if (result == null) {                      // 2nd check, under the lock
                    instance = result = new DoubleCheckedGameSettings();
                }
            }
        }
        return result;
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
