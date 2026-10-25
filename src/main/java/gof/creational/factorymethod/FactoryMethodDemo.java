package gof.creational.factorymethod;

import common.Console;

import java.util.List;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        Console.header("GoF / Factory Method -- each level subclass creates its own enemies");

        for (DungeonLevel level : List.of(new IceCaveLevel(), new FireVolcanoLevel(), new SwampLevel())) {
            String biome = level.biome();
            Console.step(biome + " level: wave of 3 + 1 reinforcement");

            List<String> wave = level.spawnWave(3).stream().map(Enemy::name).toList();
            Console.note("wave:          " + wave);

            Enemy reinforcement = level.spawnReinforcement();
            if (reinforcement.biome().equals(biome)) {
                Console.ok("reinforcement: " + reinforcement.name());
            } else {
                Console.fail("reinforcement: " + reinforcement.name() + " -- a " + reinforcement.biome()
                        + " monster in the " + biome + " level");
            }
        }

        Console.note("A new biome = one new subclass with createEnemy(). DungeonLevel itself "
                + "never changes, and there is no switch left to forget.");
    }
}
