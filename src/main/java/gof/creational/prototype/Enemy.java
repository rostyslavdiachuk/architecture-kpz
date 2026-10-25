package gof.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/** A monster in the dungeon. */
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
