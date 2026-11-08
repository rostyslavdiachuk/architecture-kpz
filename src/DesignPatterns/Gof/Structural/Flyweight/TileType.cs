namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>
/// Flyweight: the intrinsic state, shared by all tiles of one kind.
/// </summary>
/// <remarks>
/// Immutable, because it is shared: a change would affect every tile of the kind.
/// A positional <c>record</c> has init-only properties, so immutability comes for free.
/// </remarks>
public sealed record TileType(string Kind, Texture Texture, bool Walkable);
