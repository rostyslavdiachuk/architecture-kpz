package gof.behavioral.visitor;

import java.util.List;

/**
 * Visitor smell: each operation is a chain of {@code instanceof} checks.
 *
 * <p>The object types are stable, but new operations keep coming. Every operation
 * repeats the type dispatch, and when a new type appears the compiler does not
 * point at the chains that need it.
 */
public class ScoreCalculator {

    public int score(List<MapObject> objects) {
        int score = 0;
        for (MapObject object : objects) {
            if (object instanceof Monster monster) {
                score += monster.xp();
            } else if (object instanceof Chest chest) {
                score += chest.gold();
            }
            // traps give no points
        }
        return score;
    }
}
