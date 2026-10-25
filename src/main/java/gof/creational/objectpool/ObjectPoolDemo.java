package gof.creational.objectpool;

import common.Console;

public class ObjectPoolDemo {

    private static final String[] MONSTERS = {"Goblin", "Skeleton", "Orc", "Troll", "Dragon"};

    public static void main(String[] args) {
        Console.header("GoF / Object Pool -- bolts are reused through a BoltPool");

        Console.step("Loading level: BoltPool creates " + Turret.BOLTS_PER_VOLLEY + " bolts");
        Turret turret = new Turret(new BoltPool(Turret.BOLTS_PER_VOLLEY));

        for (int volley = 1; volley <= 10; volley++) {
            String target = MONSTERS[(volley - 1) % MONSTERS.length];
            turret.fireVolley(target);
            Console.step("volley " + volley + " at the " + target + " -> bolts created so far: "
                    + Bolt.created());
        }

        Console.ok("10 volleys fired with only " + Bolt.created() + " bolts -- "
                + Bolt.created() * Bolt.CREATION_COST_MS + " ms of loading, all of it before the "
                + "fight started");
        Console.note("Every reused bolt goes through reset(target): forget it and the volley at "
                + "the Troll hits the Goblin.");
    }
}
