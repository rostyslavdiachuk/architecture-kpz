package gof.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

/** A grid of tiles: walls on the border, water pools here and there, floor everywhere else. */
public class DungeonMap {

    private final List<Tile> tiles = new ArrayList<>();

    public DungeonMap(int width, int height, TextureLoader loader) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles.add(new Tile(x, y, kindAt(x, y, width, height), loader));
            }
        }
    }

    private static String kindAt(int x, int y, int width, int height) {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return "wall";
        }
        return (x * y) % 17 == 0 ? "water" : "floor";
    }

    public int size() {
        return tiles.size();
    }

    public long walkableTiles() {
        return tiles.stream().filter(Tile::walkable).count();
    }
}
