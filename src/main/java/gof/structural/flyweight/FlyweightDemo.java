package gof.structural.flyweight;

import common.Console;

public class FlyweightDemo {

    private static final int TILE_KINDS = 3;

    public static void main(String[] args) {
        Console.header("GoF / Flyweight -- tiles share one TileType per kind");

        TextureLoader loader = new TextureLoader();

        Console.step("Generating a 200 x 200 dungeon map");
        DungeonMap map = new DungeonMap(200, 200, new TileTypes(loader));
        Console.note(map.size() + " tiles, " + map.walkableTiles() + " of them walkable");

        if (loader.loads() == TILE_KINDS) {
            Console.ok(loader.loads() + " textures in memory, shared by all tiles (" + loader.memoryKb()
                    + " KB)");
        } else {
            Console.fail(loader.loads() + " textures decoded for " + TILE_KINDS + " kinds of tile");
        }

        Console.note("Still one Tile per position, but the data that repeats is stored only once. "
                + "The shared TileType is immutable, so sharing is safe.");
    }
}
