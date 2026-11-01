package gof.behavioral.strategy;

import common.Console;

public class StrategyDemo {

    private static final int DISTANCE = 5;

    public static void main(String[] args) {
        Console.header("GoF / Strategy -- each AI is a class the enemy delegates to");

        Console.step("The hero enters the room, " + DISTANCE + " tiles from each enemy");
        check(new Enemy("Orc", new Aggressive()), "charges");
        check(new Enemy("Archer", new Kiting()), "shoots an arrow");
        check(new Enemy("Necromancer", new Summoner()), "summons skeletons");

        Console.step("A goblin starts out aggressive, gets wounded, and its AI is swapped mid-fight");
        Enemy goblin = new Enemy("Goblin", new Aggressive());
        check(goblin, "charges");
        goblin.setAi(new Fleeing());
        check(goblin, "flees");

        Console.note("A new AI = one new class (or a lambda). Enemy never changes, and an "
                + "unknown behavior no longer compiles.");
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
