package gof.structural.bridge;

/** One side of the bridge: what a spell does to whoever it hits. */
public interface Element {

    String name();

    String effect();
}

final class Fire implements Element {
    public String name() { return "Fire"; }
    public String effect() { return "burns"; }
}

final class Ice implements Element {
    public String name() { return "Ice"; }
    public String effect() { return "slows"; }
}

final class Poison implements Element {
    public String name() { return "Poison"; }
    public String effect() { return "poisons"; }
}
