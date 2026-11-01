package gof.behavioral.visitor;

import common.Console;

import java.util.List;

public class VisitorDemo {

    private static final int REAL_SCORE = 170;
    private static final String REAL_MAP = "M$^S";

    public static void main(String[] args) {
        Console.header("GoF / Visitor -- every operation checks object types with instanceof");

        List<MapObject> level = List.of(new Monster("Goblin", 20), new Chest(100), new Trap(15), new Shrine(50));

        Console.step("Clearing the level: goblin (20), chest (100), trap (0), shrine (50)");
        int score = new ScoreCalculator().score(level);
        if (score == REAL_SCORE) {
            Console.ok("score: " + score);
        } else {
            Console.fail("score: " + score + " instead of " + REAL_SCORE + " -- the shrine is worth nothing here");
        }

        Console.step("Drawing the minimap");
        String map = new MinimapRenderer().render(level);
        if (map.equals(REAL_MAP)) {
            Console.ok("minimap: " + map);
        } else {
            Console.fail("minimap: " + map + " instead of " + REAL_MAP + " -- the renderer does not know shrines");
        }

        Console.note("Refactor task: a MapObjectVisitor with one visit method per type; each object "
                + "gets accept(visitor). A new type then breaks compilation in every operation.");
    }
}
