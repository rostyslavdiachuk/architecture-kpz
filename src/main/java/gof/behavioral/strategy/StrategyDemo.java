package gof.behavioral.strategy;

import common.Console;

public class StrategyDemo {

    private static final int DISTANCE = 5;

    public static void main(String[] args) {
        Console.header("GoF / Strategy -- every AI is a branch in the enemy's switch");

        Console.step("The hero enters the room, " + DISTANCE + " tiles from each enemy");
        check(new Enemy("Orc", "aggressive"), "charges");
        check(new Enemy("Archer", "kiting"), "shoots an arrow");
        check(new Enemy("Necromancer", "summoner"), "summons skeletons");

        Console.warn("a wounded goblin should start to flee, but an enemy's AI is fixed when it is created");
        Console.note("Refactor task: an AiStrategy interface with one class per behavior; the enemy "
                + "holds a strategy, delegates to it and can swap it at runtime.");
    }

    private static void check(Enemy enemy, String expected) {
        String action = enemy.act(DISTANCE);
        if (action.equals(expected)) {
            Console.ok(enemy.name() + " " + action);
        } else {
            Console.fail(enemy.name() + " " + action + ", but the level designer expected: " + expected);
        }
    }
}
