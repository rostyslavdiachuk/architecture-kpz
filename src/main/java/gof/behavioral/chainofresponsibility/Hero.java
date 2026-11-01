package gof.behavioral.chainofresponsibility;

/** The hero's defenses: an invulnerability potion, a magic shield, armor, and finally HP. */
public class Hero {

    private int hp;
    private int shield;
    private final int armor;
    private boolean invulnerable;

    public Hero(int hp, int shield, int armor) {
        this.hp = hp;
        this.shield = shield;
        this.armor = armor;
    }

    public int hp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int shield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int armor() {
        return armor;
    }

    public boolean invulnerable() {
        return invulnerable;
    }

    public void drinkInvulnerabilityPotion() {
        this.invulnerable = true;
    }
}
