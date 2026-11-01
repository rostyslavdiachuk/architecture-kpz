package gof.behavioral.strategy;

/**
 * Strategy smell: all the AI algorithms live in one {@code switch} inside the enemy.
 *
 * <p>The AI is a string from the level file. Each new behavior means editing this
 * class, the behavior cannot change during a fight, and an unknown name
 * silently falls back to charging.
 */
public class Enemy {

    private final String name;
    private final String ai;

    public Enemy(String name, String ai) {
        this.name = name;
        this.ai = ai;
    }

    public String name() {
        return name;
    }

    /** @return what the enemy does this turn, with the hero that many tiles away */
    public String act(int distanceToHero) {
        switch (ai) {
            case "aggressive":
                return distanceToHero > 1 ? "charges" : "attacks";
            case "kiting":
                return distanceToHero < 3 ? "steps back" : "shoots an arrow";
            default:
                return distanceToHero > 1 ? "charges" : "attacks";
        }
    }
}
