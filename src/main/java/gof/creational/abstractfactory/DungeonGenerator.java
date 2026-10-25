package gof.creational.abstractfactory;

/**
 * Builds a dungeon of any theme. It never names a concrete part class, it
 * only asks its factory, so every part comes from the same family.
 */
public class DungeonGenerator {

    private final DungeonThemeFactory factory;

    public DungeonGenerator(DungeonThemeFactory factory) {
        this.factory = factory;
    }

    public Dungeon generate() {
        Wall wall = factory.createWall();
        Monster monster = factory.createMonster();
        Treasure treasure = factory.createTreasure();
        return new Dungeon(wall, monster, treasure);
    }
}
