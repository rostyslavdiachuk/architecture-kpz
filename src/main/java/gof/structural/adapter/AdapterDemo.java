package gof.structural.adapter;

import common.Console;

public class AdapterDemo {

    private static final int TICKS = 3;

    public static void main(String[] args) {
        Console.header("GoF / Adapter -- the gamepad SDK is wrapped in an InputDevice");

        Console.step("Keyboard: the player holds 'right' and attack for " + TICKS + " ticks");
        Keyboard keyboard = new Keyboard();
        keyboard.holdDirection(1);
        keyboard.holdAttack(true);
        check("keyboard", new HeroController(keyboard));

        Console.step("XPad: the player pushes the stick fully right and holds A for " + TICKS + " ticks");
        XPadSdk xpad = new XPadSdk();
        xpad.pushLeftStick(XPadSdk.STICK_FULL_RIGHT);
        xpad.press(XPadSdk.BUTTON_A);
        check("gamepad", new HeroController(new XPadAdapter(xpad)));

        Console.note("A new gamepad = one new adapter. HeroController never changes, "
                + "and it does not know that the vendor's API exists.");
    }

    private static void check(String device, HeroController hero) {
        for (int i = 0; i < TICKS; i++) {
            hero.tick();
        }
        if (hero.position() == TICKS && hero.attacking()) {
            Console.ok(device + ": the hero walked " + TICKS + " tiles and is attacking");
        } else {
            Console.fail(device + ": the hero is at tile " + hero.position() + " instead of " + TICKS);
        }
    }
}
