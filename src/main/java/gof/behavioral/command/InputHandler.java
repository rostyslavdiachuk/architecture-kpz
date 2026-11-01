package gof.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Maps keys to commands and remembers what was executed.
 *
 * <p>It knows neither the hero nor potions, only {@link Command}, so any key can be
 * rebound and any action can be undone.
 */
public class InputHandler {

    private static final String UNDO_KEY = "Z";

    private final Map<String, Supplier<Command>> bindings = new HashMap<>();
    private final Deque<Command> history = new ArrayDeque<>();

    public void bind(String key, Supplier<Command> command) {
        bindings.put(key, command);
    }

    public void press(String key) {
        if (key.equals(UNDO_KEY)) {
            if (!history.isEmpty()) {
                history.pop().undo();
            }
            return;
        }
        Supplier<Command> binding = bindings.get(key);
        if (binding != null) {
            Command command = binding.get();
            command.execute();
            history.push(command);
        }
    }
}
