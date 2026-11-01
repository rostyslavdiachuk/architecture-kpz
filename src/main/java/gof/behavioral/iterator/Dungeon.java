package gof.behavioral.iterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Iterator: the dungeon hides its graph and offers ways to walk it.
 *
 * <p>Handling loops is the iterators' job, so it is written once. Each iterator has
 * its own state, so several walks can run at the same time.
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

    public int goldIn(String room) {
        return gold.get(room);
    }

    /** Rooms from nearest to farthest. */
    public Iterable<String> breadthFirst(String start) {
        return () -> new BreadthFirstIterator(start);
    }

    /** Follows one path as far as it goes, then comes back. */
    public Iterable<String> depthFirst(String start) {
        return () -> new DepthFirstIterator(start);
    }

    private final class BreadthFirstIterator implements Iterator<String> {

        private final Deque<String> queue = new ArrayDeque<>();
        private final Set<String> seen = new HashSet<>();

        private BreadthFirstIterator(String start) {
            queue.add(start);
            seen.add(start);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String room = queue.poll();
            for (String exit : exits.get(room)) {
                if (seen.add(exit)) {
                    queue.add(exit);
                }
            }
            return room;
        }
    }

    private final class DepthFirstIterator implements Iterator<String> {

        private final Deque<String> stack = new ArrayDeque<>();
        private final Set<String> visited = new HashSet<>();

        private DepthFirstIterator(String start) {
            stack.push(start);
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty() && visited.contains(stack.peek())) {
                stack.pop();
            }
            return !stack.isEmpty();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String room = stack.pop();
            visited.add(room);
            List<String> roomExits = exits.get(room);
            for (int i = roomExits.size() - 1; i >= 0; i--) {
                stack.push(roomExits.get(i));
            }
            return room;
        }
    }
}
