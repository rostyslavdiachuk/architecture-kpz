package gof.creational.abstractfactory;

/**
 * Abstract Factory smell: a copy-paste of {@link IceDungeonGenerator} for the
 * new Fire world.
 *
 * <p>Every concrete class is chosen by hand, one {@code new} at a time.
 * Nothing groups "the parts that belong together", so it is easy to change
 * two of them and miss the third.
 */
public class FireDungeonGenerator {

    public Dungeon generate() {
        Wall wall = new LavaWall();
        Monster monster = new FireImp();
        Treasure treasure = new FrostChest(); // copy-pasted from the Ice generator
        return new Dungeon(wall, monster, treasure);
    }
}
