package gof.behavioral.command;

/**
 * Command smell: a key press calls the hero directly, and then it is gone.
 *
 * <p>The action exists only as a moment in time, so it cannot be remembered,
 * undone or replayed. Keys are hard-coded in the {@code switch}, so they cannot
 * be rebound. The "undo" is a guess based on the last key.
 */
public class InputHandler {

    private final Hero hero;
    private String lastKey = "";

    public InputHandler(Hero hero) {
        this.hero = hero;
    }

    public void press(String key) {
        switch (key) {
            case "A" -> hero.moveBy(-1);
            case "D" -> hero.moveBy(1);
            case "Q" -> hero.drinkPotion();
            case "Z" -> {
                undoLast();
                return;
            }
            default -> {
                return;
            }
        }
        lastKey = key;
    }

    private void undoLast() {
        switch (lastKey) {
            case "A" -> hero.moveBy(1);
            case "D" -> hero.moveBy(-1);
            default -> {
                // no idea how to take a potion back
            }
        }
    }
}
