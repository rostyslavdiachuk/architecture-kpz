package gof.creational.abstractfactory;

// The three parts every dungeon is made of. Each part belongs to one theme.

interface Wall {
    String theme();
    String look();
}

interface Monster {
    String theme();
    String name();
}

interface Treasure {
    String theme();
    String loot();
}

final class IceWall implements Wall {
    public String theme() { return "Ice"; }
    public String look() { return "frozen ice blocks"; }
}

final class LavaWall implements Wall {
    public String theme() { return "Fire"; }
    public String look() { return "glowing lava rock"; }
}

final class IceGolem implements Monster {
    public String theme() { return "Ice"; }
    public String name() { return "Ice Golem"; }
}

final class FireImp implements Monster {
    public String theme() { return "Fire"; }
    public String name() { return "Fire Imp"; }
}

final class FrostChest implements Treasure {
    public String theme() { return "Ice"; }
    public String loot() { return "Frost Sword"; }
}

final class EmberChest implements Treasure {
    public String theme() { return "Fire"; }
    public String loot() { return "Flame Bow"; }
}
