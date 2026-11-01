package gof.behavioral.strategy;

/** Strategy: one way for an enemy to fight. */
public interface AiStrategy {

    /** @return what the enemy does this turn, with the hero that many tiles away */
    String act(int distanceToHero);
}

final class Aggressive implements AiStrategy {
    public String act(int distanceToHero) {
        return distanceToHero > 1 ? "charges" : "attacks";
    }
}

final class Kiting implements AiStrategy {
    public String act(int distanceToHero) {
        return distanceToHero < 3 ? "steps back" : "shoots an arrow";
    }
}

final class Summoner implements AiStrategy {
    public String act(int distanceToHero) {
        return "summons skeletons";
    }
}

final class Fleeing implements AiStrategy {
    public String act(int distanceToHero) {
        return "flees";
    }
}
