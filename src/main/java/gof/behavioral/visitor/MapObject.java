package gof.behavioral.visitor;

/** Something placed on a dungeon level. It knows only how to accept a visitor. */
public interface MapObject {

    <R> R accept(MapObjectVisitor<R> visitor);
}

record Monster(String name, int xp) implements MapObject {
    public <R> R accept(MapObjectVisitor<R> visitor) {
        return visitor.visitMonster(this);
    }
}

record Chest(int gold) implements MapObject {
    public <R> R accept(MapObjectVisitor<R> visitor) {
        return visitor.visitChest(this);
    }
}

record Trap(int damage) implements MapObject {
    public <R> R accept(MapObjectVisitor<R> visitor) {
        return visitor.visitTrap(this);
    }
}

record Shrine(int blessing) implements MapObject {
    public <R> R accept(MapObjectVisitor<R> visitor) {
        return visitor.visitShrine(this);
    }
}
