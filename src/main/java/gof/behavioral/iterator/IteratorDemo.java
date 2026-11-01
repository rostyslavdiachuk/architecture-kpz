package gof.behavioral.iterator;

import common.Console;

public class IteratorDemo {

    private static final int REAL_GOLD = 460;

    public static void main(String[] args) {
        Console.header("GoF / Iterator -- every client walks the dungeon graph by itself");

        Dungeon dungeon = new Dungeon()
                .room("Entrance", 0)
                .room("Hall", 10)
                .room("Armory", 50)
                .room("Crypt", 100)
                .room("Vault", 300)
                .corridor("Entrance", "Hall")
                .corridor("Hall", "Armory")
                .corridor("Armory", "Crypt")
                .corridor("Crypt", "Hall")
                .corridor("Crypt", "Vault");

        Console.step("The minimap lists the rooms, nearest first");
        Console.note(new Minimap().rooms(dungeon, "Entrance").toString());

        Console.step("The quest log counts the gold on the level");
        int gold = new GoldCounter().totalGold(dungeon, "Entrance");
        if (gold == REAL_GOLD) {
            Console.ok("the level holds " + gold + " gold");
        } else {
            Console.fail("the quest log says " + gold + " gold instead of " + REAL_GOLD
                    + " -- rooms on a loop were counted again and again");
        }

        Console.note("Refactor task: Dungeon hides its map and offers breadthFirst() and "
                + "depthFirst() iterators; clients just use a for-each loop.");
    }
}
