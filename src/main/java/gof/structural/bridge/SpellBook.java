package gof.structural.bridge;

import java.util.List;
import java.util.Optional;

/** All the spells the game knows. Every spell has to be a class of its own. */
public class SpellBook {

    private final List<Spell> spells = List.of(
            new FireBolt(), new FireNova(),
            new IceBolt(), new IceNova(),
            new PoisonBolt());

    public Optional<Spell> find(String element, String form) {
        return spells.stream()
                .filter(spell -> spell.element().equals(element) && spell.form().equals(form))
                .findFirst();
    }
}
