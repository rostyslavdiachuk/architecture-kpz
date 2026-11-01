package gof.behavioral.memento;

import java.util.List;

/**
 * A save made at the campfire. It copies the hero's fields one by one from outside.
 *
 * <p>The list is not copied, only its reference, and nobody came back here when
 * {@code xp} was added to the hero.
 */
public class Checkpoint {

    private final int hp;
    private final int gold;
    private final List<String> inventory;

    public Checkpoint(Hero hero) {
        this.hp = hero.hp();
        this.gold = hero.gold();
        this.inventory = hero.inventory();
    }

    public void restore(Hero hero) {
        hero.setHp(hp);
        hero.setGold(gold);
        hero.setInventory(inventory);
    }
}
