package gof.creational.abstractfactory;

import common.Console;

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        Console.header("GoF / Abstract Factory -- one generator, one factory per theme");

        Console.step("Generating the Ice dungeon");
        check("Ice", new DungeonGenerator(new IceThemeFactory()).generate());

        Console.step("Generating the Fire dungeon");
        check("Fire", new DungeonGenerator(new FireThemeFactory()).generate());

        Console.note("A new theme = one new DungeonThemeFactory. DungeonGenerator never changes, "
                + "and it cannot mix parts from two themes.");
    }

    private static void check(String theme, Dungeon dungeon) {
        Console.note(dungeon.toString());
        if (dungeon.themes().size() == 1) {
            Console.ok("every part of the " + theme + " dungeon is " + theme);
        } else {
            Console.fail("the " + theme + " dungeon mixes themes " + dungeon.themes());
        }
    }
}
