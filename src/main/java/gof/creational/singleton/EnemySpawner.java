package gof.creational.singleton;

/** Spawns enemies, scaling their HP by the selected difficulty. */
public class EnemySpawner {

    private static final int BASE_GOBLIN_HP = 40;

    private final GameSettings settings = new GameSettings();

    /** @return HP of the goblin that was spawned */
    public int spawnGoblin() {
        return (int) Math.round(BASE_GOBLIN_HP * settings.difficulty().enemyHpMultiplier());
    }

    public Difficulty difficultyInUse() {
        return settings.difficulty();
    }
}
