package gof.creational.abstractfactory;

/** Builds an Ice dungeon by creating each part with {@code new}. */
public class IceDungeonGenerator {

    public Dungeon generate() {
        Wall wall = new IceWall();
        Monster monster = new IceGolem();
        Treasure treasure = new FrostChest();
        return new Dungeon(wall, monster, treasure);
    }
}
