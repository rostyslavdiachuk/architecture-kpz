package gof.creational.abstractfactory;

import common.Console;

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        Console.header("GoF / Abstract Factory -- one copy-pasted generator per theme");

        Console.step("Generating the Ice dungeon");
        check("Ice", new IceDungeonGenerator().generate());

        Console.step("Generating the Fire dungeon");
        check("Fire", new FireDungeonGenerator().generate());

        Console.note("Refactor task: one DungeonThemeFactory per theme that creates the whole "
                + "family (wall, monster, treasure); one DungeonGenerator that only uses the factory.");
    }

    private static void check(String theme, Dungeon dungeon) {
        Console.note(dungeon.toString());
        if (dungeon.themes().size() == 1) {
            Console.ok("every part of the " + theme + " dungeon is " + theme);
        } else {
            Console.fail("the " + theme + " dungeon mixes themes " + dungeon.themes()
                    + " -- a " + dungeon.treasure().loot() + " is lying in the lava");
        }
    }
}
