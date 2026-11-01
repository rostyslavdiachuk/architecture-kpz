package gof.behavioral.iterator;

import java.util.ArrayDeque;
import java.util.Deque;

/** Sums up the gold on the level. Another developer, another traversal. */
public class GoldCounter {

    // the game hung on the first dungeon with a loop, so a limit was added
    private static final int MAX_STEPS = 100;

    public int totalGold(Dungeon dungeon, String start) {
        int total = 0;
        int steps = 0;
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty() && steps++ < MAX_STEPS) {
            String room = stack.pop();
            total += dungeon.goldIn(room);
            for (String exit : dungeon.exits().get(room)) {
                stack.push(exit);
            }
        }
        return total;
    }
}
