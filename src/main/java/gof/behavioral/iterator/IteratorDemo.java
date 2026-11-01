package gof.behavioral.iterator;

import common.Console;

public class IteratorDemo {

    private static final int REAL_GOLD = 460;

    public static void main(String[] args) {
        Console.header("GoF / Iterator -- the dungeon knows how to walk itself");

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
                    + "");
        }

        Console.note("The clients are for-each loops now. The loop handling is written once, "
                + "and the rooms could be stored differently without changing a client.");
    }
}
