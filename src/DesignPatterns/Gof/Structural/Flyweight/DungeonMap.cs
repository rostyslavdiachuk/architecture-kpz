namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>A grid of tiles: walls on the border, water pools here and there, floor everywhere else.</summary>
public class DungeonMap
{
    private readonly Tile[] _tiles;

    public DungeonMap(int width, int height, TileTypes types)
    {
        _tiles = new Tile[width * height];
        var i = 0;
        for (var x = 0; x < width; x++)
        {
            for (var y = 0; y < height; y++)
            {
                _tiles[i++] = new Tile(x, y, types.Get(KindAt(x, y, width, height)));
            }
        }
    }

    private static string KindAt(int x, int y, int width, int height)
    {
        if (x == 0 || y == 0 || x == width - 1 || y == height - 1)
        {
            return "wall";
        }
        return (x * y) % 17 == 0 ? "water" : "floor";
    }

    public int Size => _tiles.Length;

    public int WalkableTiles => _tiles.Count(tile => tile.Walkable);

    public Tile At(int index) => _tiles[index];
}
