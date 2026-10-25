package gof.creational.builder;

import common.Console;

public class BuilderDemo {

    public static void main(String[] args) {
        Console.header("GoF / Builder -- heroes are built step by step");

        Console.step("Character creator: Aria, a mage with 80 HP, 150 mana, a Fire Staff and an owl");
        Hero aria = Hero.builder("Aria", HeroClass.MAGE)
                .hp(80)
                .mana(150)
                .strength(5)
                .weapon("Fire Staff")
                .pet("Owl")
                .build();
        Console.note(aria.toString());
        Console.ok("Aria got " + aria.hp() + " HP and " + aria.mana() + " mana -- every value is "
                + "named, so there is nothing to swap");

        Console.step("Character creator: Borin, a warrior with an axe and plate armor, no pet");
        Hero borin = Hero.builder("Borin", HeroClass.WARRIOR)
                .hp(200)
                .strength(18)
                .weapon("Battle Axe")
                .armor("Plate Armor")
                .build();
        Console.note(borin.toString());
        Console.ok("the call reads like the description, and the parts he does not have "
                + "(mana, pet) are simply not mentioned");

        Console.step("Character creator: Nox, a mage... someone typed 0 mana");
        try {
            Hero.builder("Nox", HeroClass.MAGE).hp(60).mana(0).weapon("Wand").build();
            Console.fail("a mage with 0 mana was created");
        } catch (IllegalStateException e) {
            Console.ok("build() refused: " + e.getMessage() + " -- a broken hero never exists");
        }
    }
}
