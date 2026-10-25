package gof.creational.objectpool;

import common.Console;

public class ObjectPoolDemo {

    private static final String[] MONSTERS = {"Goblin", "Skeleton", "Orc", "Troll", "Dragon"};

    public static void main(String[] args) {
        Console.header("GoF / Object Pool -- new bolts for every volley");

        Turret turret = new Turret();

        for (int volley = 1; volley <= 10; volley++) {
            String target = MONSTERS[(volley - 1) % MONSTERS.length];
            turret.fireVolley(target);
            Console.step("volley " + volley + " at the " + target + " -> bolts created so far: "
                    + Bolt.created());
        }

        Console.fail("a volley needs only " + Turret.BOLTS_PER_VOLLEY + " bolts, yet " + Bolt.created()
                + " were created -- " + Bolt.created() * Bolt.CREATION_COST_MS
                + " ms spent loading the same sprite again and again");
        Console.note("Refactor task: create " + Turret.BOLTS_PER_VOLLEY + " bolts once in a BoltPool; "
                + "a volley takes bolts from the pool and puts them back after they hit.");
    }
}
