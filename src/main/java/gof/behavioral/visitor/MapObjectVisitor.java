package gof.behavioral.visitor;

/**
 * Visitor: one operation over all kinds of map objects.
 *
 * <p>A new kind of object adds a method here, so the compiler points at every
 * operation that has to handle it.
 */
public interface MapObjectVisitor<R> {

    R visitMonster(Monster monster);

    R visitChest(Chest chest);

    R visitTrap(Trap trap);

    R visitShrine(Shrine shrine);
}
