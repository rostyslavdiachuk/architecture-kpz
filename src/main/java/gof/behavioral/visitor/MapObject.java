package gof.behavioral.visitor;

/** Something placed on a dungeon level. */
public interface MapObject {
}

record Monster(String name, int xp) implements MapObject {
}

record Chest(int gold) implements MapObject {
}

record Trap(int damage) implements MapObject {
}

/** The newest kind of object. Blessing the hero is worth points. */
record Shrine(int blessing) implements MapObject {
}
