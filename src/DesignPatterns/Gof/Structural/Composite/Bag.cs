using System.Collections;

namespace DesignPatterns.Gof.Structural.Composite;

/// <summary>The composite: asks its children, each of which can be an item or another bag.</summary>
/// <remarks>
/// C# specific: implementing <see cref="IEnumerable{T}"/> and having an <c>Add</c> method
/// enables collection-initializer syntax, so a tree reads like the tree it is:
/// <code>
/// new Bag("Pouch", 1) { new Item("Ruby", 1, 500), new Item("Gold Bar", 5, 800) }
/// </code>
/// </remarks>
public class Bag(string name, int ownWeight) : IInventoryEntry, IEnumerable<IInventoryEntry>
{
    private readonly List<IInventoryEntry> _contents = [];

    public string Name { get; } = name;

    public int Weight => ownWeight + _contents.Sum(entry => entry.Weight);

    public int Value => _contents.Sum(entry => entry.Value);

    public IEnumerable<IInventoryEntry> Children => _contents;

    public void Add(IInventoryEntry entry) => _contents.Add(entry);

    /// <summary>
    /// C# specific: a lazy, recursive walk with <c>yield return</c>. Every entry of the
    /// tree, at any depth, comes out as one flat sequence that LINQ can query.
    /// </summary>
    public IEnumerable<IInventoryEntry> Flatten() => Walk(this);

    private static IEnumerable<IInventoryEntry> Walk(IInventoryEntry node)
    {
        foreach (var child in node.Children)
        {
            yield return child;
            foreach (var inner in Walk(child))
            {
                yield return inner;
            }
        }
    }

    public IEnumerator<IInventoryEntry> GetEnumerator() => _contents.GetEnumerator();

    IEnumerator IEnumerable.GetEnumerator() => GetEnumerator();
}
