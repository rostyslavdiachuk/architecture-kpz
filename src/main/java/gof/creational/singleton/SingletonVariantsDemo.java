package gof.creational.singleton;

import common.Console;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Loading screen of Dungeon Raid: 8 subsystems start on 8 threads and all ask
 * for the settings at the same moment. How many instances does each variant create?
 */
public class SingletonVariantsDemo {

    private static final int THREADS = 8;

    public static void main(String[] args) throws InterruptedException {
        Console.header("GoF / Singleton -- 8 threads call getInstance() at the same moment");

        race("Naive lazy", NaiveGameSettings::getInstance, NaiveGameSettings::instancesCreated);
        race("Double-checked locking", DoubleCheckedGameSettings::getInstance,
                DoubleCheckedGameSettings::instancesCreated);
        race("Enum", () -> EnumGameSettings.INSTANCE, EnumGameSettings::instancesCreated);

        Console.note("Recommended: enum (when no superclass is needed) or the holder idiom used "
                + "by GameSettings -- both are thread-safe with no locking code to get wrong.");
    }

    private static void race(String variant, Supplier<Object> getInstance, IntSupplier instancesCreated)
            throws InterruptedException {
        Console.step(variant + ": starting " + THREADS + " threads");
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        CountDownLatch start = new CountDownLatch(1);

        try (ExecutorService pool = Executors.newFixedThreadPool(THREADS)) {
            for (int i = 0; i < THREADS; i++) {
                pool.submit(() -> {
                    start.await();
                    seen.add(getInstance.get());
                    return null;
                });
            }
            start.countDown(); // release all threads at once
        }

        int created = instancesCreated.getAsInt();
        if (created == 1 && seen.size() == 1) {
            Console.ok(variant + ": 1 instance created, all threads got the same one");
        } else {
            Console.fail(variant + ": " + created + " instances created, threads saw " + seen.size()
                    + " different objects -- each subsystem may now hold its own settings");
        }
    }
}
