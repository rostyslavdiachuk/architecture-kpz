package gof.creational.factorymethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory Method: the level owns the spawning algorithm, and each subclass
 * decides which enemy to create by overriding {@link #createEnemy()}.
 *
 * <p>Both spawning methods go through the same factory method, so they can
 * never disagree. A new biome is a new subclass, and because
 * {@code createEnemy()} is abstract, the compiler makes you implement it.
 */
public abstract class DungeonLevel {

    public abstract String biome();

    /** The factory method. */
    protected abstract Enemy createEnemy();

    public List<Enemy> spawnWave(int size) {
        List<Enemy> wave = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            wave.add(createEnemy());
        }
        return wave;
    }

    public Enemy spawnReinforcement() {
        return createEnemy();
    }
}
