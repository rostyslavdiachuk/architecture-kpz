package gof.structural.decorator;

import common.Console;

public class DecoratorDemo {

    private static final int FIRE_BONUS = 5;
    private static final int SHARP_BONUS = 3;

    public static void main(String[] args) {
        Console.header("GoF / Decorator -- one weapon class per combination of enchantments");

        Forge forge = new Forge();

        order(forge, "Sword", 10, true, false);
        order(forge, "Axe", 14, false, true);
        order(forge, "Sword", 10, true, true);
        order(forge, "Axe", 14, true, true);

        Console.note("Refactor task: keep only Sword and Axe; make FireEnchant and SharpEnchant "
                + "decorators that wrap any Weapon and add their bonus.");
    }

    private static void order(Forge forge, String base, int baseDamage, boolean fire, boolean sharp) {
        String enchants = (fire ? "Fire " : "") + (sharp ? "Sharp " : "");
        int expected = baseDamage + (fire ? FIRE_BONUS : 0) + (sharp ? SHARP_BONUS : 0);
        Console.step("Order: " + enchants + base + " (should deal " + expected + ")");

        Weapon weapon = forge.forge(base, fire, sharp);
        if (weapon.damage() == expected) {
            Console.ok(weapon.name() + " deals " + weapon.damage());
        } else {
            Console.fail("got '" + weapon.name() + "' with " + weapon.damage() + " damage -- the player "
                    + "paid for both enchantments");
        }
    }
}
