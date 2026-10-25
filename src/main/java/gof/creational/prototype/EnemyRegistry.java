package gof.creational.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype registry: spawning an enemy means copying a ready-made prototype.
 *
 * <p>The registry knows no concrete enemy kinds. Anything registered, including
 * a variant the level designer built at runtime, can be spawned.
 */
public class EnemyRegistry {

    private final Map<String, Enemy> prototypes = new HashMap<>();

    public void register(String name, Enemy prototype) {
        prototypes.put(name, prototype);
    }

    public Enemy spawn(String name) {
        Enemy prototype = prototypes.get(name);
        if (prototype == null) {
            throw new IllegalArgumentException("no prototype registered for '" + name + "'");
        }
        return prototype.copy();
    }
}
