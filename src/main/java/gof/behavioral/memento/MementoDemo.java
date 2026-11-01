package gof.behavioral.memento;

import common.Console;

public class MementoDemo {

    public static void main(String[] args) {
        Console.header("GoF / Memento -- the checkpoint copies the hero's fields from outside");

        Hero hero = new Hero();
        String atCampfire = hero.toString();
        Console.step("The hero rests at the campfire, and the game saves: " + atCampfire);
        Checkpoint checkpoint = new Checkpoint(hero);

        Console.step("The hero fights the boss, drinks the potion, and then loses");
        hero.fightTheBoss();
        Console.note(hero.toString());

        Console.step("The player reloads the checkpoint");
        checkpoint.restore(hero);
        Console.note(hero.toString());

        if (hero.toString().equals(atCampfire)) {
            Console.ok("the hero is back to exactly how they were at the campfire");
        } else {
            if (!hero.inventory().contains("Potion")) {
                Console.fail("the potion is gone -- the checkpoint saved a reference to the live list");
            }
            if (hero.xp() != 100) {
                Console.fail("xp is " + hero.xp() + " instead of 100 -- the checkpoint does not know about xp");
            }
        }

        Console.note("Refactor task: the hero creates its own opaque snapshot (Hero.save()) and "
                + "restores from it (Hero.restore()); the caretaker just keeps the snapshots.");
    }
}
