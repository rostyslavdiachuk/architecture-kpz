package gof.behavioral.iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Lists the rooms from nearest to farthest. This traversal is correct, but it is its own copy. */
public class Minimap {

    public List<String> rooms(Dungeon dungeon, String start) {
        List<String> order = new ArrayList<>();
        Set<String> seen = new HashSet<>(Set.of(start));
        Deque<String> queue = new ArrayDeque<>(List.of(start));
        while (!queue.isEmpty()) {
            String room = queue.poll();
            order.add(room);
            for (String exit : dungeon.exits().get(room)) {
                if (seen.add(exit)) {
                    queue.add(exit);
                }
            }
        }
        return order;
    }
}
