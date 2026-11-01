package gof.behavioral.iterator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Iterator smell: the dungeon hands out its internal graph, and every client
 * writes its own traversal.
 *
 * <p>Rooms are stored as an adjacency map, and the corridors form loops. Each
 * client has to know both facts, so changing the storage breaks all of them.
 */
public class Dungeon {

    private final Map<String, List<String>> exits = new LinkedHashMap<>();
    private final Map<String, Integer> gold = new LinkedHashMap<>();

    public Dungeon room(String name, int goldInRoom) {
        exits.put(name, new ArrayList<>());
        gold.put(name, goldInRoom);
        return this;
    }

    public Dungeon corridor(String a, String b) {
        exits.get(a).add(b);
        exits.get(b).add(a);
        return this;
    }

    public Map<String, List<String>> exits() {
        return exits;
    }

    public int goldIn(String room) {
        return gold.get(room);
    }
}
