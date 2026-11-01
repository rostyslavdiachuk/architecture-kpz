package gof.behavioral.command;

import common.Console;

public class CommandDemo {

    public static void main(String[] args) {
        Console.header("GoF / Command -- key presses call the hero directly");

        Hero hero = new Hero(50, 1);
        InputHandler input = new InputHandler(hero);
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
        Console.warn("the player also wants to move with the arrow keys, but keys are hard-coded "
                + "in a switch");

        Console.note("Refactor task: a Command object per action with execute() and undo(); the "
                + "handler maps keys to commands and keeps a history stack.");
    }
}
