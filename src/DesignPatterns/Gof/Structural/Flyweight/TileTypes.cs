namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>The flyweight factory: creates each kind once, then hands out the same instance.</summary>
/// <remarks>
/// Not thread-safe. If tiles were generated on several threads, swap the dictionary for a
/// <see cref="System.Collections.Concurrent.ConcurrentDictionary{TKey,TValue}"/> and call
/// <c>GetOrAdd(kind, Create)</c>.
/// </remarks>
public class TileTypes(TextureLoader loader)
{
    private readonly Dictionary<string, TileType> _types = [];

    public int Count => _types.Count;

    public TileType Get(string kind)
    {
        if (!_types.TryGetValue(kind, out var type))
        {
            type = new TileType(kind, loader.Load(kind + ".png"), kind != "wall");
            _types[kind] = type;
        }
        return type;
    }
}
