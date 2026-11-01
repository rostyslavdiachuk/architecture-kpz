package gof.behavioral.command;

/** A hero on a turn-based board. */
public class Hero {

    private static final int MAX_HP = 100;
    private static final int POTION_HEAL = 30;

    private int position;
    private int hp;
    private int potions;

    public Hero(int hp, int potions) {
        this.hp = hp;
        this.potions = potions;
    }

    public void moveBy(int tiles) {
        position += tiles;
    }

    public void drinkPotion() {
        potions--;
        hp = Math.min(MAX_HP, hp + POTION_HEAL);
    }

    public int position() {
        return position;
    }

    public int hp() {
        return hp;
    }

    public int potions() {
        return potions;
    }

    @Override
    public String toString() {
        return "tile " + position + ", " + hp + " HP, " + potions + " potion(s)";
    }
}
