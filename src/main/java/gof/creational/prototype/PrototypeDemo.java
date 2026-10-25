package gof.creational.prototype;

import common.Console;

import java.util.ArrayList;
import java.util.List;

public class PrototypeDemo {

    public static void main(String[] args) {
        Console.header("GoF / Prototype -- every enemy is built from scratch");

        AssetLoader assets = new AssetLoader();
        EnemySpawner spawner = new EnemySpawner(assets);

        Console.step("Level designer builds a boss in the editor: a goblin with 3x HP, "
                + "60% ice resistance and 'Blizzard'");
        Enemy frostKing = spawner.spawn("Goblin");
        frostKing.rename("Frost Goblin King");
        frostKing.stats().setHp(frostKing.stats().hp() * 3);
        frostKing.stats().setIceResistance(60);
        frostKing.abilities().add("Blizzard");
        Console.note("designed: " + frostKing);

        Console.header("Wave 1: spawn 20 goblins");
        int loadsBefore = assets.loads();
        List<Enemy> wave = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            wave.add(spawner.spawn("Goblin"));
        }
        int waveLoads = assets.loads() - loadsBefore;
        Console.fail("goblin.mesh was read from disk " + waveLoads + " times for 20 identical goblins"
                + " (~" + waveLoads * 100 + " ms of loading -- the wave appears with a freeze)");

        Console.header("Boss room: spawn the 'Frost Goblin King'");
        Enemy boss = spawner.spawn("Frost Goblin King");
        Console.note("spawned:  " + boss);
        Console.fail("the boss came out as a plain goblin -- the designer's HP, resistance and "
                + "Blizzard are lost, because the spawner only knows the cases in its switch");

        Console.note("Refactor task: let enemies copy themselves (Enemy.copy()) and keep "
                + "ready-made prototypes in a registry; spawning = cloning a prototype.");
    }
}
