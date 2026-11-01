package gof.structural.bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** All the spells the game knows: every form combined with every element. */
public class SpellBook {

    private final List<Spell> spells = new ArrayList<>();

    public SpellBook() {
        for (Element element : List.of(new Fire(), new Ice(), new Poison())) {
            spells.add(new Bolt(element));
            spells.add(new Nova(element));
        }
    }

    public Optional<Spell> find(String element, String form) {
        return spells.stream()
                .filter(spell -> spell.element().equals(element) && spell.form().equals(form))
                .findFirst();
    }
}
