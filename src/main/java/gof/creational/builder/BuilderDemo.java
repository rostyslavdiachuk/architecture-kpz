package gof.creational.builder;

import common.Console;

public class BuilderDemo {

    public static void main(String[] args) {
        Console.header("GoF / Builder -- heroes from an 8-argument constructor");

        Console.step("Character creator: Aria, a mage with 80 HP, 150 mana, a Fire Staff and an owl");
        Hero aria = new Hero("Aria", HeroClass.MAGE, 150, 80, 5, "Fire Staff", null, "Owl");
        Console.note(aria.toString());
        Console.fail("Aria got " + aria.hp() + " HP and " + aria.mana() + " mana -- the two ints "
                + "are swapped, and the compiler cannot tell");

        Console.step("Character creator: Borin, a warrior with an axe and plate armor, no pet");
        Hero borin = new Hero("Borin", HeroClass.WARRIOR, 200, 0, 18, "Battle Axe", "Plate Armor", null);
        Console.note(borin.toString());
        Console.warn("read the call: new Hero(\"Borin\", WARRIOR, 200, 0, 18, \"Battle Axe\", "
                + "\"Plate Armor\", null) -- which number is which, and what is that null?");

        Console.step("Character creator: Nox, a mage... someone typed 0 mana");
        Hero nox = new Hero("Nox", HeroClass.MAGE, 60, 0, 4, "Wand", null, null);
        Console.note(nox.toString());
        Console.fail("a mage with 0 mana was created -- there is no step where the hero is checked "
                + "before it exists");

        Console.note("Refactor task: Hero.builder(name, class).hp(80).mana(150)...build() -- "
                + "named steps, defaults for optional parts, validation in build().");
    }
}
