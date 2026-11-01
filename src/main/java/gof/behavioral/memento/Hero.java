package gof.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento smell: to make saving possible, the hero exposes its internals through
 * setters and hands out its own inventory list.
 *
 * <p>Anyone can now change the hero's state from outside, and whoever
 * saves it has to know every field, including the ones added later.
 */
public class Hero {

    private int hp = 100;
    private int gold = 50;
    private int xp = 100; // added after Checkpoint had been written
    private List<String> inventory = new ArrayList<>(List.of("Sword", "Potion"));

    public void fightTheBoss() {
        inventory.remove("Potion");
        hp -= 80;
        gold += 300;
        xp += 200;
    }

    public int hp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int gold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int xp() {
        return xp;
    }

    public List<String> inventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "hp=" + hp + ", gold=" + gold + ", xp=" + xp + ", inventory=" + inventory;
    }
}
