namespace DesignPatterns.Gof.Structural.Composite;

/// <summary>The leaf: a single thing the hero carries.</summary>
/// <remarks>The record's positional properties implement the interface directly.</remarks>
public sealed record Item(string Name, int Weight, int Value) : IInventoryEntry;
