package gof.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento, the originator: the hero saves and restores itself.
 *
 * <p>A new field changes only this class. There are no setters, and the inventory
 * list never leaves the hero.
 */
public class Hero {

    /** The frozen state. Only {@link Hero} can read it; to everyone else it is opaque. */
    public static final class Memento {

        private final int hp;
        private final int gold;
        private final int xp;
        private final List<String> inventory;

        private Memento(Hero hero) {
            this.hp = hero.hp;
            this.gold = hero.gold;
            this.xp = hero.xp;
            this.inventory = List.copyOf(hero.inventory);
        }
    }

    private int hp = 100;
    private int gold = 50;
    private int xp = 100;
    private List<String> inventory = new ArrayList<>(List.of("Sword", "Potion"));

    public void fightTheBoss() {
        inventory.remove("Potion");
        hp -= 80;
        gold += 300;
        xp += 200;
    }

    public Memento save() {
        return new Memento(this);
    }

    public void restore(Memento memento) {
        hp = memento.hp;
        gold = memento.gold;
        xp = memento.xp;
        inventory = new ArrayList<>(memento.inventory);
    }

    @Override
    public String toString() {
        return "hp=" + hp + ", gold=" + gold + ", xp=" + xp + ", inventory=" + inventory;
    }
}
