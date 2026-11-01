package gof.structural.decorator;

// The base weapons, and the enchantments that can wrap any weapon.

final class Sword implements Weapon {
    public String name() { return "Sword"; }
    public int damage() { return 10; }
}

final class Axe implements Weapon {
    public String name() { return "Axe"; }
    public int damage() { return 14; }
}

/** Decorator: is a {@link Weapon} and wraps a {@link Weapon}, so it can be wrapped again. */
abstract class EnchantedWeapon implements Weapon {

    protected final Weapon inner;

    protected EnchantedWeapon(Weapon inner) {
        this.inner = inner;
    }
}

final class FireEnchant extends EnchantedWeapon {

    FireEnchant(Weapon inner) {
        super(inner);
    }

    public String name() { return "Fire " + inner.name(); }
    public int damage() { return inner.damage() + 5; }
}

final class SharpEnchant extends EnchantedWeapon {

    SharpEnchant(Weapon inner) {
        super(inner);
    }

    public String name() { return "Sharp " + inner.name(); }
    public int damage() { return inner.damage() + 3; }
}
