package gof.structural.bridge;

/**
 * Bridge: the form of a spell and its element vary independently, so they live in
 * two hierarchies, connected by the {@code element} field.
 *
 * <p>A new form or a new element is one class, and it combines with everything on
 * the other side.
 */
public abstract class Spell {

    private final Element element;

    protected Spell(Element element) {
        this.element = element;
    }

    public abstract String form();

    public abstract String targets();

    public String element() {
        return element.name();
    }

    public String effect() {
        return element.effect();
    }

    public String cast() {
        return element() + " " + form() + " hits " + targets() + " and " + effect() + " them";
    }
}

final class Bolt extends Spell {

    Bolt(Element element) {
        super(element);
    }

    public String form() { return "Bolt"; }
    public String targets() { return "one enemy"; }
}

final class Nova extends Spell {

    Nova(Element element) {
        super(element);
    }

    public String form() { return "Nova"; }
    public String targets() { return "the whole room"; }
}
