package gof.behavioral.chainofresponsibility;

import common.Console;

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        Console.header("GoF / Chain of Responsibility -- the whole damage pipeline in one method");

        DamageResolver resolver = new DamageResolver();

        Console.step("A troll hits for 30: 20 go into the shield, armor stops 5, HP takes 5");
        Hero hero = new Hero(100, 20, 5);
        resolver.resolve(hero, 30);
        if (hero.shield() == 0 && hero.hp() == 95) {
            Console.ok("shield " + hero.shield() + ", HP " + hero.hp());
        } else {
            Console.fail("shield " + hero.shield() + ", HP " + hero.hp());
        }

        Console.step("The hero drinks an invulnerability potion, and the troll hits for 30 again");
        Hero protectedHero = new Hero(100, 20, 5);
        protectedHero.drinkInvulnerabilityPotion();
        resolver.resolve(protectedHero, 30);
        if (protectedHero.shield() == 20 && protectedHero.hp() == 100) {
            Console.ok("the hit stopped at the potion: shield " + protectedHero.shield()
                    + ", HP " + protectedHero.hp());
        } else {
            Console.fail("the hero is invulnerable, but the shield dropped to " + protectedHero.shield()
                    + " -- the potion is checked after the shield took the hit");
        }

        Console.note("Refactor task: one DamageHandler per step, each with a link to the next; "
                + "the potion goes first and simply does not pass the hit on.");
    }
}
