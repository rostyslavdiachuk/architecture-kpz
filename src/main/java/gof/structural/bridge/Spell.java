package gof.structural.bridge;

/**
 * Bridge smell: two independent ideas are folded into one class hierarchy.
 *
 * <p>A spell has a <b>form</b> (Bolt, Nova) and an <b>element</b> (Fire, Ice,
 * Poison). Here each combination is a class, so forms x elements classes are
 * needed, and the targets and the effect are copied into each of them.
 */
public interface Spell {

    String element();

    String form();

    String targets();

    String effect();

    default String cast() {
        return element() + " " + form() + " hits " + targets() + " and " + effect() + " them";
    }
}

final class FireBolt implements Spell {
    public String element() { return "Fire"; }
    public String form() { return "Bolt"; }
    public String targets() { return "one enemy"; }
    public String effect() { return "burns"; }
}

final class FireNova implements Spell {
    public String element() { return "Fire"; }
    public String form() { return "Nova"; }
    public String targets() { return "the whole room"; }
    public String effect() { return "burns"; }
}

final class IceBolt implements Spell {
    public String element() { return "Ice"; }
    public String form() { return "Bolt"; }
    public String targets() { return "one enemy"; }
    public String effect() { return "slows"; }
}

final class IceNova implements Spell {
    public String element() { return "Ice"; }
    public String form() { return "Nova"; }
    public String targets() { return "the whole room"; }
    public String effect() { return "burns"; } // copy-pasted from FireNova
}

final class PoisonBolt implements Spell {
    public String element() { return "Poison"; }
    public String form() { return "Bolt"; }
    public String targets() { return "one enemy"; }
    public String effect() { return "poisons"; }
}

// PoisonNova: the new element shipped without it
