using DesignPatterns.Common;

namespace DesignPatterns.Gof.Structural.Flyweight;

public static class FlyweightDemo
{
    private const int TileKinds = 3;

    public static void Run()
    {
        Out.Header("GoF / Flyweight -- every tile carries its own copy of the texture");

        var loader = new TextureLoader();

        Out.Step("Generating a 200 x 200 dungeon map");
        var map = new DungeonMap(200, 200, loader);
        Out.Note($"{map.Size} tiles, {map.WalkableTiles} of them walkable");

        if (loader.Loads == TileKinds)
        {
            Out.Ok($"{loader.Loads} textures in memory, shared by all tiles ({loader.MemoryKb} KB)");
        }
        else
        {
            Out.Fail($"{loader.Loads} textures decoded for {TileKinds} kinds of tile -- "
                     + $"{loader.MemoryKb / 1024} MB of memory, and most of it is the same three images");
        }

        Out.Note("Refactor task: move kind, texture and walkable into a shared TileType; a "
                 + "TileTypes factory hands out one instance per kind; a Tile keeps X, Y and a reference.");
    }
}
