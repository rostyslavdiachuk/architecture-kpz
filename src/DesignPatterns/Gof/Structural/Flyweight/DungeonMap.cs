namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>A grid of tiles: walls on the border, water pools here and there, floor everywhere else.</summary>
public class DungeonMap
{
    private readonly List<Tile> _tiles = [];

    public DungeonMap(int width, int height, TextureLoader loader)
    {
        for (var x = 0; x < width; x++)
        {
            for (var y = 0; y < height; y++)
            {
                _tiles.Add(new Tile(x, y, KindAt(x, y, width, height), loader));
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

    public int Size => _tiles.Count;

    public int WalkableTiles => _tiles.Count(tile => tile.Walkable);
}
