package gof.structural.bridge;

import common.Console;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BridgeDemo {

    private static final Map<String, String> EFFECTS = Map.of("Fire", "burns", "Ice", "slows", "Poison", "poisons");

    public static void main(String[] args) {
        Console.header("GoF / Bridge -- one class per element and form combination");

        SpellBook book = new SpellBook();

        for (String element : List.of("Fire", "Ice", "Poison")) {
            for (String form : List.of("Bolt", "Nova")) {
                Optional<Spell> spell = book.find(element, form);
                if (spell.isEmpty()) {
                    Console.fail(element + " " + form + ": there is no such class -- the spell cannot be cast");
                } else if (!spell.get().effect().equals(EFFECTS.get(element))) {
                    Console.fail(spell.get().cast() + " -- that is not what " + element + " does");
                } else {
                    Console.ok(spell.get().cast());
                }
            }
        }

        Console.note("Refactor task: split into two hierarchies: Spell (Bolt, Nova) holds an "
                + "Element (Fire, Ice, Poison). 2 forms + 3 elements = 5 classes, 6 spells.");
    }
}
