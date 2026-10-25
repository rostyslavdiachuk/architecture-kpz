package gof.creational.builder;

/**
 * Builder smell: one constructor with eight positional parameters.
 *
 * <p>Three of them are {@code int}s and three are {@code String}s, so swapping
 * two of them compiles fine. Optional parts are passed as {@code null}, and
 * there is no good place to check that the result is a valid hero.
 */
public class Hero {

    private final String name;
    private final HeroClass heroClass;
    private final int hp;
    private final int mana;
    private final int strength;
    private final String weapon;
    private final String armor;
    private final String pet;

    public Hero(String name, HeroClass heroClass, int hp, int mana, int strength,
                String weapon, String armor, String pet) {
        this.name = name;
        this.heroClass = heroClass;
        this.hp = hp;
        this.mana = mana;
        this.strength = strength;
        this.weapon = weapon;
        this.armor = armor;
        this.pet = pet;
    }

    public String name() {
        return name;
    }

    public HeroClass heroClass() {
        return heroClass;
    }

    public int hp() {
        return hp;
    }

    public int mana() {
        return mana;
    }

    @Override
    public String toString() {
        return name + " the " + heroClass + " [hp=" + hp + ", mana=" + mana + ", str=" + strength
                + ", weapon=" + weapon + ", armor=" + armor + ", pet=" + pet + "]";
    }
}
