package gof.structural.facade;

/**
 * Facade: one simple entry point in front of the engine's subsystems.
 *
 * <p>The correct startup sequence exists only here. No button has to know it.
 */
public class GameSession {

    private final AssetLoader assets = new AssetLoader();
    private final WorldGenerator world = new WorldGenerator();
    private final EnemySpawner spawner = new EnemySpawner();
    private final AudioEngine audio = new AudioEngine();
    private final Hud hud = new Hud();

    public void startLevel(String level) {
        assets.load(level);
        world.generate(assets);
        spawner.populate(world);
        audio.playTheme(level);
        hud.show();
    }

    public int enemies() {
        return spawner.enemies();
    }

    public String theme() {
        return audio.theme();
    }
}
