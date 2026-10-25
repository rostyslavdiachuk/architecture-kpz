package gof.creational.objectpool;

import common.Console;

public class ObjectPoolDemo {

    public static void main(String[] args) {
        Console.header("GoF / Object Pool -- a new bolt for every shot");

        Turret turret = new Turret();

        Console.step("Boss fight: the turret fires 1 bolt per frame for 600 frames (10 s at 60 FPS)");
        for (int frame = 0; frame < 600; frame++) {
            turret.fire();
            turret.update();
        }

        Console.note("bolts in the air at once (max): " + turret.maxInAir());
        Console.note("bolts created:                  " + Bolt.created());
        Console.fail("only " + turret.maxInAir() + " bolts are ever needed, yet " + Bolt.created()
                + " were created -- " + Bolt.created() * Bolt.CREATION_COST_MS
                + " ms spent loading the same sprite again and again");
        Console.note("Refactor task: create the bolts once in a BoltPool; fire() takes a bolt from "
                + "the pool, a bolt that hits the wall goes back to the pool.");
    }
}
