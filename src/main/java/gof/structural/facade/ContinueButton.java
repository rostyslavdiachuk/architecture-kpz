package gof.structural.facade;

/**
 * Written later by another developer, who rebuilt the startup sequence from memory:
 * the enemies are spawned too early and the music was forgotten.
 */
public class ContinueButton {

    private final AssetLoader assets;
    private final WorldGenerator world;
    private final EnemySpawner spawner;
    private final Hud hud;

    ContinueButton(AssetLoader assets, WorldGenerator world, EnemySpawner spawner, Hud hud) {
        this.assets = assets;
        this.world = world;
        this.spawner = spawner;
        this.hud = hud;
    }

    public void onClick() {
        spawner.populate(world);
        assets.load("Crypt");
        world.generate(assets);
        hud.show();
    }
}
