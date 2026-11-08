namespace DesignPatterns.Gof.Structural.Composite;

/// <summary>A container with its own weight. It can hold items and other bags.</summary>
public class Bag(string name, int weight)
{
    private readonly List<object> _contents = [];

    public string Name { get; } = name;

    public int Weight { get; } = weight;

    public IReadOnlyList<object> Contents => _contents;

    public Bag Add(object itemOrBag)
    {
        _contents.Add(itemOrBag);
        return this;
    }
}
