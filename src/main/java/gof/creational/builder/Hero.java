package gof.creational.builder;

/**
 * Builder: a hero is assembled step by step with named methods, then validated
 * and created in one go by {@link Builder#build()}.
 *
 * <p>The constructor is private, so the builder is the only way in. The hero
 * is immutable, and it can never exist in an invalid state.
 */
public final class Hero {

    private final String name;
    private final HeroClass heroClass;
    private final int hp;
    private final int mana;
    private final int strength;
    private final String weapon;
    private final String armor;
    private final String pet;

    private Hero(Builder builder) {
        this.name = builder.name;
        this.heroClass = builder.heroClass;
        this.hp = builder.hp;
        this.mana = builder.mana;
        this.strength = builder.strength;
        this.weapon = builder.weapon;
        this.armor = builder.armor;
        this.pet = builder.pet;
    }

    /** Required parts go here; everything else is optional. */
    public static Builder builder(String name, HeroClass heroClass) {
        return new Builder(name, heroClass);
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

    public static final class Builder {

        private final String name;
        private final HeroClass heroClass;
        private int hp = 100;
        private int mana = 0;
        private int strength = 10;
        private String weapon = "Fists";
        private String armor = "Cloth";
        private String pet = "none";

        private Builder(String name, HeroClass heroClass) {
            this.name = name;
            this.heroClass = heroClass;
        }

        public Builder hp(int hp) {
            this.hp = hp;
            return this;
        }

        public Builder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public Builder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder weapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        public Builder armor(String armor) {
            this.armor = armor;
            return this;
        }

        public Builder pet(String pet) {
            this.pet = pet;
            return this;
        }

        public Hero build() {
            if (hp <= 0) {
                throw new IllegalStateException(name + ": a hero needs HP > 0");
            }
            if (heroClass == HeroClass.MAGE && mana <= 0) {
                throw new IllegalStateException(name + ": a mage needs mana > 0");
            }
            return new Hero(this);
        }
    }
}
