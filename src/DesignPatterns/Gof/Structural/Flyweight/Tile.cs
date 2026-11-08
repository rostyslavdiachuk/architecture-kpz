namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>
/// Flyweight smell: each tile keeps its own copy of data that is the same for
/// every tile of its kind.
/// </summary>
/// <remarks>
/// Only <see cref="X"/> and <see cref="Y"/> are unique to a tile. The kind, the texture and
/// the walkable flag are identical for all floor tiles, yet each tile holds them.
/// </remarks>
public class Tile
{
    public Tile(int x, int y, string kind, TextureLoader loader)
    {
        X = x;
        Y = y;
        Kind = kind;
        Texture = loader.Load(kind + ".png");
        Walkable = kind != "wall";
    }

    public int X { get; }

    public int Y { get; }

    public string Kind { get; }

    public Texture Texture { get; }

    public bool Walkable { get; }
}
