package gof.creational.abstractfactory;

/**
 * Abstract Factory: creates a whole family of dungeon parts that belong together.
 *
 * <p>One implementation per theme. Whoever holds a factory can only get parts
 * of that one theme, so mixing is impossible.
 */
public interface DungeonThemeFactory {

    Wall createWall();

    Monster createMonster();

    Treasure createTreasure();
}

final class IceThemeFactory implements DungeonThemeFactory {
    public Wall createWall() { return new IceWall(); }
    public Monster createMonster() { return new IceGolem(); }
    public Treasure createTreasure() { return new FrostChest(); }
}

final class FireThemeFactory implements DungeonThemeFactory {
    public Wall createWall() { return new LavaWall(); }
    public Monster createMonster() { return new FireImp(); }
    public Treasure createTreasure() { return new EmberChest(); }
}
