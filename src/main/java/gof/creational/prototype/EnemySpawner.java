package gof.creational.prototype;

import common.Console;

import java.util.List;

/**
 * Prototype smell: every enemy is built from scratch, and only the kinds that
 * are hard-coded in a {@code switch} can be built at all.
 *
 * <p>Each spawn loads the mesh from disk again, although all goblins look the
 * same. And a variant the level designer configured at runtime exists only as
 * an object, not as a {@code case}, so the spawner cannot reproduce it.
 */
public class EnemySpawner {

    private final AssetLoader assets;

    public EnemySpawner(AssetLoader assets) {
        this.assets = assets;
    }

    public Enemy spawn(String type) {
        switch (type) {
            case "Goblin":
                return new Enemy("Goblin", assets.loadMesh("goblin.mesh"),
                        new Stats(40, 6, 0), List.of("Stab"));
            case "Skeleton Archer":
                return new Enemy("Skeleton Archer", assets.loadMesh("skeleton.mesh"),
                        new Stats(30, 9, 25), List.of("Arrow Volley"));
            default:
                Console.warn("unknown enemy type '" + type + "' -> falling back to Goblin");
                return spawn("Goblin");
        }
    }
}
