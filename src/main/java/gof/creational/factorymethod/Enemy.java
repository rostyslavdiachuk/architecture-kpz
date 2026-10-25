package gof.creational.factorymethod;

/** A monster that lives in one biome. */
public interface Enemy {

    String name();

    String biome();
}

final class Goblin implements Enemy {
    public String name() { return "Goblin"; }
    public String biome() { return "Cave"; }
}

final class IceGolem implements Enemy {
    public String name() { return "Ice Golem"; }
    public String biome() { return "Ice"; }
}

final class FireImp implements Enemy {
    public String name() { return "Fire Imp"; }
    public String biome() { return "Fire"; }
}

final class SwampTroll implements Enemy {
    public String name() { return "Swamp Troll"; }
    public String biome() { return "Swamp"; }
}
