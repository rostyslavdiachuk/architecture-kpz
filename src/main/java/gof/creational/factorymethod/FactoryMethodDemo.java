package gof.creational.factorymethod;

import common.Console;

import java.util.List;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        Console.header("GoF / Factory Method -- the level picks enemy classes with a switch");

        for (String biome : List.of("Ice", "Fire", "Swamp")) {
            DungeonLevel level = new DungeonLevel(biome);
            Console.step(biome + " level: wave of 3 + 1 reinforcement");

            List<String> wave = level.spawnWave(3).stream().map(Enemy::name).toList();
            Console.note("wave:          " + wave);

            Enemy reinforcement = level.spawnReinforcement();
            if (reinforcement.biome().equals(biome)) {
                Console.ok("reinforcement: " + reinforcement.name());
            } else {
                Console.fail("reinforcement: " + reinforcement.name() + " -- a " + reinforcement.biome()
                        + " monster in the " + biome + " level; the second switch was never updated");
            }
        }

        Console.note("Refactor task: make DungeonLevel abstract with a factory method "
                + "createEnemy(); each biome is a subclass that decides which enemy to create.");
    }
}
