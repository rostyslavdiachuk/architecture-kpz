using DesignPatterns.Common;

namespace DesignPatterns.Gof.Structural.Composite;

public static class CompositeDemo
{
    private const int CarryLimit = 15;
    private const int RealWeight = 19;
    private const int RealValue = 1950;

    public static void Run()
    {
        Out.Header("GoF / Composite -- an item and a bag answer the same questions");

        Out.Step("The hero carries a Sword and a Backpack with a Potion and a Pouch of treasure");
        // Collection initializers (C# specific): the code has the same shape as the tree.
        var inventory = new Bag("Inventory", 0)
        {
            new Item("Sword", 8, 100),
            new Bag("Backpack", 2)
            {
                new Item("Potion", 1, 50),
                new Bag("Pouch", 1)
                {
                    new Item("Ruby", 1, 500),
                    new Item("Ruby", 1, 500),
                    new Item("Gold Bar", 5, 800),
                },
            },
        };

        var weight = inventory.Weight;
        var value = inventory.Value;

        if (weight == RealWeight)
        {
            Out.Ok($"weight: {weight}" + (weight > CarryLimit ? " -- over the limit, the hero is slowed" : ""));
        }
        else
        {
            Out.Fail($"weight: {weight} instead of {RealWeight}");
        }

        if (value == RealValue)
        {
            Out.Ok($"the merchant offers {value} gold for everything");
        }
        else
        {
            Out.Fail($"the merchant offers {value} gold instead of {RealValue}");
        }

        Out.Note("Bag asks its children and does not care what they are, "
                 + "so any depth works, and there are no type checks left.");

        Out.Step("C# extra: Flatten() walks the tree with yield return, so LINQ sees every level");
        var items = inventory.Flatten().OfType<Item>().ToList();
        var best = items.MaxBy(item => item.Value)!;
        Out.Note($"{items.Count} items at any depth, {items.Count(item => item.Name == "Ruby")} of them rubies");
        Out.Ok($"most valuable item: {best.Name} ({best.Value} gold)");
    }
}
