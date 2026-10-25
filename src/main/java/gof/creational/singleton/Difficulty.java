package gof.creational.singleton;

/** How hard the dungeon fights back. */
public enum Difficulty {
    EASY(0.75),
    NORMAL(1.0),
    HARD(1.5);

    private final double enemyHpMultiplier;

    Difficulty(double enemyHpMultiplier) {
        this.enemyHpMultiplier = enemyHpMultiplier;
    }

    public double enemyHpMultiplier() {
        return enemyHpMultiplier;
    }
}
