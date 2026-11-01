package gof.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/** Lists the rooms from nearest to farthest. */
public class Minimap {

    public List<String> rooms(Dungeon dungeon, String start) {
        List<String> order = new ArrayList<>();
        for (String room : dungeon.breadthFirst(start)) {
            order.add(room);
        }
        return order;
    }
}
