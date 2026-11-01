package gof.behavioral.chainofresponsibility;

import common.Console;

public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        Console.header("GoF / Chain of Responsibility -- the damage passes through a chain of handlers");

        DamageHandler chain = new InvulnerabilityHandler();
        chain.linkWith(new ShieldHandler())
                .linkWith(new ArmorHandler())
                .linkWith(new HealthHandler());

        Console.step("A troll hits for 30: 20 go into the shield, armor stops 5, HP takes 5");
        Hero hero = new Hero(100, 20, 5);
        chain.handle(hero, 30);
        if (hero.shield() == 0 && hero.hp() == 95) {
            Console.ok("shield " + hero.shield() + ", HP " + hero.hp());
        } else {
            Console.fail("shield " + hero.shield() + ", HP " + hero.hp());
        }

        Console.step("The hero drinks an invulnerability potion, and the troll hits for 30 again");
        Hero protectedHero = new Hero(100, 20, 5);
        protectedHero.drinkInvulnerabilityPotion();
        chain.handle(protectedHero, 30);
        if (protectedHero.shield() == 20 && protectedHero.hp() == 100) {
            Console.ok("the hit stopped at the potion: shield " + protectedHero.shield()
                    + ", HP " + protectedHero.hp());
        } else {
            Console.fail("the hero is invulnerable, but the shield dropped to " + protectedHero.shield()
                    + "");
        }

        Console.note("A new defense = one new handler and one link. A ghost that ignores armor "
                + "simply uses a chain without ArmorHandler.");
    }
}
