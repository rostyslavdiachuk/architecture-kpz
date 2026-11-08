using DesignPatterns.Common;

namespace DesignPatterns.Gof.Structural.Flyweight;

public static class FlyweightDemo
{
    private const int TileKinds = 3;
    private const int Width = 200;
    private const int Height = 200;

    public static void Run()
    {
        Out.Header("GoF / Flyweight -- tiles share one TileType per kind");

        var loader = new TextureLoader();
        var types = new TileTypes(loader);

        Out.Step($"Generating a {Width} x {Height} dungeon map");
        var before = GC.GetAllocatedBytesForCurrentThread();
        var map = new DungeonMap(Width, Height, types);
        var structBytes = GC.GetAllocatedBytesForCurrentThread() - before;
        Out.Note($"{map.Size} tiles, {map.WalkableTiles} of them walkable");

        if (loader.Loads == TileKinds)
        {
            Out.Ok($"{loader.Loads} textures in memory, shared by all tiles ({loader.MemoryKb} KB)");
        }
        else
        {
            Out.Fail($"{loader.Loads} textures decoded for {TileKinds} kinds of tile");
        }

        var (a, b) = (map.At(Height * 1 + 1), map.At(Height * 5 + 7));   // floor at (1,1) and (5,7)
        if (ReferenceEquals(a.Type, b.Type))
        {
            Out.Ok($"tiles ({a.X},{a.Y}) and ({b.X},{b.Y}) hold the very same {a.Type.Kind} TileType instance");
        }
        else
        {
            Out.Fail($"tiles ({a.X},{a.Y}) and ({b.X},{b.Y}) hold separate TileType copies");
        }

        Out.Note("Still one Tile per position, but the data that repeats is stored only once. "
                 + "The shared TileType is immutable, so sharing is safe.");

        StructVersusClass(types, structBytes);
        StringInterning();
    }

    /// <summary>C# specific: the extrinsic part can be a value type, so it costs no object per tile.</summary>
    private static void StructVersusClass(TileTypes types, long structBytes)
    {
        Out.Step("C# extra: what if Tile were a class instead of a readonly record struct?");

        var floor = types.Get("floor");
        var before = GC.GetAllocatedBytesForCurrentThread();
        var objects = new TileObject[Width * Height];
        for (var i = 0; i < objects.Length; i++)
        {
            objects[i] = new TileObject(i / Height, i % Height, floor);
        }
        var classBytes = GC.GetAllocatedBytesForCurrentThread() - before;
        GC.KeepAlive(objects);

        Out.Note($"struct Tile[]: {structBytes / 1024} KB -- one array, the tiles live inside it");
        Out.Note($"class  Tile[]: {classBytes / 1024} KB -- an array of references plus "
                 + $"{objects.Length} objects with a header each, for the GC to track");
    }

    /// <summary>C# specific: the runtime already ships a flyweight factory for strings.</summary>
    private static void StringInterning()
    {
        Out.Step("C# extra: the string intern pool is a built-in flyweight factory");

        const string literal = "floor";
        var built = new string("floor".AsSpan());   // same text, a brand-new object
        var interned = string.Intern(built);       // ask the pool for the shared instance

        Out.Note($"literal == built by value: {literal == built}, same object: {ReferenceEquals(literal, built)}");
        Out.Ok($"string.Intern(built) is the literal's instance: {ReferenceEquals(literal, interned)}");
    }

    private sealed record TileObject(int X, int Y, TileType Type);
}
