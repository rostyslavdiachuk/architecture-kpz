package common;

/**
 * Tiny logging helper so the demo output reads well on a projector.
 *
 * <p>This class is intentionally trivial and is <b>not</b> part of any lesson.
 * It only prefixes lines so the narrative of each demo is easy to follow:
 * <pre>
 *   ===  section header
 *   &gt;&gt;   a step the system performs
 *   [OK] the system behaved correctly
 *   [WARN] something suspicious but not fatal
 *   [FAIL] the system misbehaved (wrong result, dropped work, crash)
 *   ..   a plain note
 * </pre>
 */
public final class Console {

    private Console() {
    }

    public static void header(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }

    public static void step(String message) {
        System.out.println(">>  " + message);
    }

    public static void ok(String message) {
        System.out.println("[OK] " + message);
    }

    public static void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public static void fail(String message) {
        System.out.println("[FAIL] " + message);
    }

    public static void note(String message) {
        System.out.println("..  " + message);
    }
}
