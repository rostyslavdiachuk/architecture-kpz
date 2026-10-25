package gof.creational.prototype;

import common.Console;

import java.util.ArrayList;
import java.util.List;

public class PrototypeDemo {

    public static void main(String[] args) {
        Console.header("GoF / Prototype -- enemies are cloned from registered prototypes");

        AssetLoader assets = new AssetLoader();
        EnemyRegistry registry = new EnemyRegistry();

        Console.step("Loading level: one prototype per enemy kind");
        registry.register("Goblin", new Enemy("Goblin", assets.loadMesh("goblin.mesh"),
                new Stats(40, 6, 0), List.of("Stab")));
        registry.register("Skeleton Archer", new Enemy("Skeleton Archer", assets.loadMesh("skeleton.mesh"),
                new Stats(30, 9, 25), List.of("Arrow Volley")));

        Console.step("Level designer builds a boss in the editor: a goblin with 3x HP, "
                + "60% ice resistance and 'Blizzard'");
        Enemy frostKing = registry.spawn("Goblin");
        frostKing.rename("Frost Goblin King");
        frostKing.stats().setHp(frostKing.stats().hp() * 3);
        frostKing.stats().setIceResistance(60);
        frostKing.abilities().add("Blizzard");
        registry.register("Frost Goblin King", frostKing);
        Console.note("designed: " + frostKing);

        Console.header("Wave 1: spawn 20 goblins");
        int loadsBefore = assets.loads();
        List<Enemy> wave = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            wave.add(registry.spawn("Goblin"));
        }
        int waveLoads = assets.loads() - loadsBefore;
        Console.ok("20 goblins spawned with " + waveLoads + " extra disk reads -- every clone "
                + "shares the immutable goblin.mesh loaded once with the prototype");

        Console.step("The hero hits the first goblin for 30 damage");
        wave.get(0).takeDamage(30);
        long untouched = wave.stream().skip(1).filter(g -> g.stats().hp() == 40).count();
        Console.ok("goblin #1 has " + wave.get(0).stats().hp() + " HP, the other " + untouched
                + " still have 40 -- each clone owns a deep copy of its Stats");

        Console.header("Boss room: spawn the 'Frost Goblin King'");
        Enemy boss = registry.spawn("Frost Goblin King");
        Console.note("spawned:  " + boss);
        Console.ok("the boss is exactly what the designer built -- the registry clones any "
                + "registered prototype, even one created at runtime");
    }
}
