namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>The extrinsic state: only the position is unique to a tile, the rest is shared.</summary>
/// <remarks>
/// C# specific: a <c>readonly record struct</c> is a value type. A <c>Tile[]</c> stores the
/// 40,000 tiles inline (two ints and one reference each), with no object per tile, so even the
/// unique part costs as little as it can.
/// </remarks>
public readonly record struct Tile(int X, int Y, TileType Type)
{
    public bool Walkable => Type.Walkable;
}
