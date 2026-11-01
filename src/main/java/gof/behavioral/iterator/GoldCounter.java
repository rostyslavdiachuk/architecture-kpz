package gof.behavioral.iterator;

/** Sums up the gold on the level. It doesn't care how rooms are connected or stored. */
public class GoldCounter {

    public int totalGold(Dungeon dungeon, String start) {
        int total = 0;
        for (String room : dungeon.depthFirst(start)) {
            total += dungeon.goldIn(room);
        }
        return total;
    }
}
