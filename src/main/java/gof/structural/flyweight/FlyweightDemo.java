package gof.structural.flyweight;

import common.Console;

public class FlyweightDemo {

    private static final int TILE_KINDS = 3;

    public static void main(String[] args) {
        Console.header("GoF / Flyweight -- every tile carries its own copy of the texture");

        TextureLoader loader = new TextureLoader();

        Console.step("Generating a 200 x 200 dungeon map");
        DungeonMap map = new DungeonMap(200, 200, loader);
        Console.note(map.size() + " tiles, " + map.walkableTiles() + " of them walkable");

        if (loader.loads() == TILE_KINDS) {
            Console.ok(loader.loads() + " textures in memory, shared by all tiles (" + loader.memoryKb()
                    + " KB)");
        } else {
            Console.fail(loader.loads() + " textures decoded for " + TILE_KINDS + " kinds of tile -- "
                    + loader.memoryKb() / 1024 + " MB of memory, and most of it is the same three images");
        }

        Console.note("Refactor task: move kind, texture and walkable into a shared TileType; a "
                + "TileTypes factory hands out one instance per kind; a Tile keeps x, y and a reference.");
    }
}
