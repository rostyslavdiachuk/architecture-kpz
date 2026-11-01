package gof.structural.adapter;

import common.Console;

public class AdapterDemo {

    private static final int TICKS = 3;

    public static void main(String[] args) {
        Console.header("GoF / Adapter -- the hero controller talks to the gamepad SDK directly");

        Console.step("Keyboard: the player holds 'right' and attack for " + TICKS + " ticks");
        Keyboard keyboard = new Keyboard();
        keyboard.holdDirection(1);
        keyboard.holdAttack(true);
        check("keyboard", new HeroController(keyboard));

        Console.step("XPad: the player pushes the stick fully right and holds A for " + TICKS + " ticks");
        XPadSdk xpad = new XPadSdk();
        xpad.pushLeftStick(XPadSdk.STICK_FULL_RIGHT);
        xpad.press(XPadSdk.BUTTON_A);
        check("gamepad", new HeroController(xpad));

        Console.note("Refactor task: write XPadAdapter implements InputDevice; HeroController "
                + "keeps one InputDevice field and loses the if/else.");
    }

    private static void check(String device, HeroController hero) {
        for (int i = 0; i < TICKS; i++) {
            hero.tick();
        }
        if (hero.position() == TICKS && hero.attacking()) {
            Console.ok(device + ": the hero walked " + TICKS + " tiles and is attacking");
        } else {
            Console.fail(device + ": the hero is at tile " + hero.position() + " instead of " + TICKS
                    + " -- the raw stick value was used as a distance");
        }
    }
}
