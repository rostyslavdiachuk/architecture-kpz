namespace DesignPatterns.Gof.Structural.Composite;

/// <summary>
/// Composite smell: items and bags have nothing in common, so the client has to
/// check types and walk the structure by hand.
/// </summary>
/// <remarks>
/// Both totals were written when a bag could only hold items. Each one looks
/// exactly one level deep, so a bag inside a bag is counted as an empty bag.
/// </remarks>
public class Inventory
{
    private readonly List<object> _entries = [];

    public Inventory Add(object itemOrBag)
    {
        _entries.Add(itemOrBag);
        return this;
    }

    public int TotalWeight()
    {
        var total = 0;
        foreach (var entry in _entries)
        {
            if (entry is Item item)
            {
                total += item.Weight;
            }
            else if (entry is Bag bag)
            {
                total += bag.Weight;
                foreach (var inner in bag.Contents)
                {
                    if (inner is Item innerItem)
                    {
                        total += innerItem.Weight;
                    }
                }
            }
        }
        return total;
    }

    public int TotalValue()
    {
        var total = 0;
        foreach (var entry in _entries)
        {
            if (entry is Item item)
            {
                total += item.Value;
            }
            else if (entry is Bag bag)
            {
                foreach (var inner in bag.Contents)
                {
                    if (inner is Item innerItem)
                    {
                        total += innerItem.Value;
                    }
                }
            }
        }
        return total;
    }
}
