package gof.creational.factorymethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory Method smell: the level decides which enemy class to {@code new}
 * with a {@code switch} on the biome, and that switch lives in two places.
 *
 * <p>When the Swamp biome was added, the developer updated {@link #spawnWave}
 * but missed {@link #spawnReinforcement}. Nothing reminded them: a string
 * switch with a {@code default} compiles either way.
 */
public class DungeonLevel {

    private final String biome;

    public DungeonLevel(String biome) {
        this.biome = biome;
    }

    public String biome() {
        return biome;
    }

    public List<Enemy> spawnWave(int size) {
        List<Enemy> wave = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Enemy enemy = switch (biome) {
                case "Ice" -> new IceGolem();
                case "Fire" -> new FireImp();
                case "Swamp" -> new SwampTroll();
                default -> new Goblin();
            };
            wave.add(enemy);
        }
        return wave;
    }

    public Enemy spawnReinforcement() {
        // copied from spawnWave() back when there were only Ice and Fire levels
        return switch (biome) {
            case "Ice" -> new IceGolem();
            case "Fire" -> new FireImp();
            default -> new Goblin();
        };
    }
}
