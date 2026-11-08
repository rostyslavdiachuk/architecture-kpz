namespace DesignPatterns.Gof.Structural.Composite;

/// <summary>
/// Composite: an item and a whole bag of items look the same from the outside.
/// </summary>
/// <remarks>
/// Clients read <see cref="Weight"/> or <see cref="Value"/> and don't care which one
/// they have or how deep the tree goes.
/// </remarks>
public interface IInventoryEntry
{
    string Name { get; }

    int Weight { get; }

    int Value { get; }

    /// <summary>
    /// C# specific: a default interface member. A leaf has no children, so <see cref="Item"/>
    /// does not have to write anything; only <see cref="Bag"/> overrides it.
    /// </summary>
    IEnumerable<IInventoryEntry> Children => [];
}
