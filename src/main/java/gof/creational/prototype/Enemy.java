package gof.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * A monster in the dungeon that can copy itself (the Prototype).
 *
 * <p>{@link #copy()} deep-copies what each enemy changes on its own, its
 * {@link Stats} and its abilities, and shares the immutable {@link Mesh}. So
 * the expensive mesh is loaded once, and hitting one clone never hurts another.
 */
public class Enemy {

    private String name;
    private final Mesh mesh;
    private final Stats stats;
    private final List<String> abilities;

    public Enemy(String name, Mesh mesh, Stats stats, List<String> abilities) {
        this.name = name;
        this.mesh = mesh;
        this.stats = stats;
        this.abilities = new ArrayList<>(abilities);
    }

    private Enemy(Enemy prototype) {
        this.name = prototype.name;
        this.mesh = prototype.mesh;                    // immutable -> shared
        this.stats = new Stats(prototype.stats);       // mutable   -> deep copy
        this.abilities = new ArrayList<>(prototype.abilities);
    }

    public Enemy copy() {
        return new Enemy(this);
    }

    public String name() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public Mesh mesh() {
        return mesh;
    }

    public Stats stats() {
        return stats;
    }

    public List<String> abilities() {
        return abilities;
    }

    public void takeDamage(int amount) {
        stats.setHp(Math.max(0, stats.hp() - amount));
    }

    @Override
    public String toString() {
        return name + " [" + stats + ", abilities=" + abilities + ", mesh=" + mesh.file() + "]";
    }
}
