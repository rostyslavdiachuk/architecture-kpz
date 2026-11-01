package gof.behavioral.visitor;

import common.Console;

import java.util.List;
import java.util.stream.Collectors;

public class VisitorDemo {

    private static final int REAL_SCORE = 170;
    private static final String REAL_MAP = "M$^S";

    public static void main(String[] args) {
        Console.header("GoF / Visitor -- each operation is a visitor with one method per type");

        List<MapObject> level = List.of(new Monster("Goblin", 20), new Chest(100), new Trap(15), new Shrine(50));

        Console.step("Clearing the level: goblin (20), chest (100), trap (0), shrine (50)");
        int score = level.stream().mapToInt(object -> object.accept(new ScoreVisitor())).sum();
        if (score == REAL_SCORE) {
            Console.ok("score: " + score);
        } else {
            Console.fail("score: " + score + " instead of " + REAL_SCORE + "");
        }

        Console.step("Drawing the minimap");
        String map = level.stream().map(object -> object.accept(new MinimapVisitor())).collect(Collectors.joining());
        if (map.equals(REAL_MAP)) {
            Console.ok("minimap: " + map);
        } else {
            Console.fail("minimap: " + map + " instead of " + REAL_MAP + "");
        }

        Console.note("A new operation = one new visitor. A new object type adds a method to the interface, "
                + "so the compiler lists each visitor that must handle it.");
    }
}
