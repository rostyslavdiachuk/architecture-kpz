package gof.creational.singleton;

/** Pretends to parse settings.cfg. Slow on purpose, so race conditions show up. */
final class SettingsFile {

    private SettingsFile() {
    }

    static void parse() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
