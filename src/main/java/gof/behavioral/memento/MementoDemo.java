package gof.behavioral.memento;

import common.Console;

public class MementoDemo {

    public static void main(String[] args) {
        Console.header("GoF / Memento -- the hero saves and restores itself");

        Hero hero = new Hero();
        Campfire campfire = new Campfire();
        String atCampfire = hero.toString();
        Console.step("The hero rests at the campfire, and the game saves: " + atCampfire);
        campfire.rest(hero);

        for (int attempt = 1; attempt <= 2; attempt++) {
            Console.step("Attempt " + attempt + ": the hero fights the boss, drinks the potion, and then loses");
            hero.fightTheBoss();
            Console.note(hero.toString());

            Console.step("The player reloads the checkpoint");
            campfire.reload(hero);
            if (hero.toString().equals(atCampfire)) {
                Console.ok("back to exactly how they were at the campfire: " + hero);
            } else {
                Console.fail("the save was damaged: " + hero);
            }
        }

        Console.note("The campfire never sees a field of the hero. A new Hero field changes only "
                + "Hero and its Memento.");
    }
}
