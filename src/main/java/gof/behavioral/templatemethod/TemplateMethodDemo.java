package gof.behavioral.templatemethod;

import common.Console;

import java.util.List;
import java.util.function.Supplier;

public class TemplateMethodDemo {

    public static void main(String[] args) {
        Console.header("GoF / Template Method -- each fighter has its own copy of the turn rules");

        for (Supplier<Fighter> newFighter : List.<Supplier<Fighter>>of(Warrior::new, Mage::new, Rogue::new)) {
            Fighter stunned = newFighter.get();
            Console.step(stunned.name() + " takes a turn while stunned, then another one while poisoned");

            stunned.stun();
            String action = stunned.takeTurn();
            if (action.equals("skips the turn")) {
                Console.ok("stunned: " + action);
            } else {
                Console.fail("stunned, but " + action);
            }

            Fighter poisoned = newFighter.get();
            poisoned.poison();
            poisoned.takeTurn();
            if (poisoned.hp() == 100 - Fighter.POISON_DAMAGE) {
                Console.ok("poisoned: HP " + poisoned.hp());
            } else {
                Console.fail("poisoned, but HP is still " + poisoned.hp());
            }
        }

        Console.note("Refactor task: Fighter gets a final takeTurn() with the shared rules, which "
                + "calls an abstract act(); subclasses write only their action.");
    }
}
