package gof.creational.abstractfactory;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** A generated dungeon. It looks right only if all its parts share one theme. */
record Dungeon(Wall wall, Monster monster, Treasure treasure) {

    Set<String> themes() {
        return new TreeSet<>(List.of(wall.theme(), monster.theme(), treasure.theme()));
    }

    @Override
    public String toString() {
        return "walls of " + wall.look() + ", guarded by " + monster.name()
                + ", chest with a " + treasure.loot();
    }
}
