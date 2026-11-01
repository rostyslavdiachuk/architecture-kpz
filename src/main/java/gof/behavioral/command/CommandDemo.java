package gof.behavioral.command;

import common.Console;

public class CommandDemo {

    public static void main(String[] args) {
        Console.header("GoF / Command -- every action is an object with execute() and undo()");

        Hero hero = new Hero(50, 1);
        InputHandler input = new InputHandler();
        input.bind("A", () -> new MoveCommand(hero, -1));
        input.bind("D", () -> new MoveCommand(hero, 1));
        input.bind("Q", () -> new DrinkPotionCommand(hero));
        String start = hero.toString();
        Console.note("start of the turn: " + start);

        Console.step("The player presses D, D, Q (two steps right, drink a potion)");
        for (String key : new String[]{"D", "D", "Q"}) {
            input.press(key);
        }
        Console.note(hero.toString());

        Console.step("The player changes their mind and presses Z three times");
        for (int i = 0; i < 3; i++) {
            input.press("Z");
        }

        if (hero.toString().equals(start)) {
            Console.ok("the whole turn was taken back: " + hero);
        } else {
            Console.fail("three undos later: " + hero + " -- it should be " + start);
        }
        Console.step("The player rebinds moving right to the arrow key and presses it");
        input.bind("RIGHT", () -> new MoveCommand(hero, 1));
        input.press("RIGHT");
        if (hero.position() == 1) {
            Console.ok("the new key works: " + hero);
        } else {
            Console.fail("the new key does nothing: " + hero);
        }

        Console.note("Once actions are objects, a history gives you undo, "
                + "and the same objects could be replayed or sent over the network.");
    }
}
