package gof.structural.decorator;

// Decorator smell: one class per combination of base weapon and enchantments.
// Fire adds +5 damage, Sharp adds +3. Every number is copied by hand.

final class Sword implements Weapon {
    public String name() { return "Sword"; }
    public int damage() { return 10; }
}

final class Axe implements Weapon {
    public String name() { return "Axe"; }
    public int damage() { return 14; }
}

final class FireSword implements Weapon {
    public String name() { return "Fire Sword"; }
    public int damage() { return 15; }
}

final class FireAxe implements Weapon {
    public String name() { return "Fire Axe"; }
    public int damage() { return 19; }
}

final class SharpSword implements Weapon {
    public String name() { return "Sharp Sword"; }
    public int damage() { return 13; }
}

final class SharpAxe implements Weapon {
    public String name() { return "Sharp Axe"; }
    public int damage() { return 17; }
}

final class FireSharpSword implements Weapon {
    public String name() { return "Fire Sharp Sword"; }
    public int damage() { return 15; } // copy-pasted from FireSword
}

// FireSharpAxe: nobody has written it yet
