package gof.structural.facade;

/**
 * Facade smell: a menu button must know every engine subsystem and the exact
 * order to call them in.
 */
public class MainMenu {

    private final AssetLoader assets;
    private final WorldGenerator world;
    private final EnemySpawner spawner;
    private final AudioEngine audio;
    private final Hud hud;

    MainMenu(AssetLoader assets, WorldGenerator world, EnemySpawner spawner, AudioEngine audio, Hud hud) {
        this.assets = assets;
        this.world = world;
        this.spawner = spawner;
        this.audio = audio;
        this.hud = hud;
    }

    public void onNewGame() {
        assets.load("Crypt");
        world.generate(assets);
        spawner.populate(world);
        audio.playTheme("Crypt");
        hud.show();
    }
}
