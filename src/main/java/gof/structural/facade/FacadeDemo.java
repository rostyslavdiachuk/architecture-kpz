package gof.structural.facade;

import common.Console;

public class FacadeDemo {

    public static void main(String[] args) {
        Console.header("GoF / Facade -- the buttons only talk to GameSession");

        Console.step("First launch: the player clicks 'New game'");
        GameSession session = new GameSession();
        new MainMenu(session).onNewGame();
        check(session.enemies(), session.theme());

        Console.step("Next day: the game starts again and the player clicks 'Continue'");
        session = new GameSession();
        new ContinueButton(session).onClick();
        check(session.enemies(), session.theme());

        Console.note("The startup sequence exists in one place. A sixth subsystem changes only "
                + "GameSession, and no button can get the order wrong.");
    }

    private static void check(int enemies, String theme) {
        if (enemies > 0) {
            Console.ok("the level has " + enemies + " enemies");
        } else {
            Console.fail("the level has no enemies");
        }
        if (theme != null) {
            Console.ok("the " + theme + " theme is playing");
        } else {
            Console.fail("the level is silent");
        }
    }
}
