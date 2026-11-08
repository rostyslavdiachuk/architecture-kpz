using DesignPatterns.Common;

namespace DesignPatterns.Gof.Structural.Composite;

public static class CompositeDemo
{
    private const int CarryLimit = 15;
    private const int RealWeight = 19;
    private const int RealValue = 1950;

    public static void Run()
    {
        Out.Header("GoF / Composite -- the inventory checks types and looks one level deep");

        var pouch = new Bag("Pouch", 1)
            .Add(new Item("Ruby", 1, 500))
            .Add(new Item("Ruby", 1, 500))
            .Add(new Item("Gold Bar", 5, 800));
        var backpack = new Bag("Backpack", 2)
            .Add(new Item("Potion", 1, 50))
            .Add(pouch);

        Out.Step("The hero carries a Sword and a Backpack with a Potion and a Pouch of treasure");
        var inventory = new Inventory()
            .Add(new Item("Sword", 8, 100))
            .Add(backpack);

        var weight = inventory.TotalWeight();
        var value = inventory.TotalValue();

        if (weight == RealWeight)
        {
            Out.Ok($"weight: {weight}" + (weight > CarryLimit ? " -- over the limit, the hero is slowed" : ""));
        }
        else
        {
            Out.Fail($"weight: {weight} instead of {RealWeight} -- the hero is over the limit of "
                     + $"{CarryLimit} but runs at full speed");
        }

        if (value == RealValue)
        {
            Out.Ok($"the merchant offers {value} gold for everything");
        }
        else
        {
            Out.Fail($"the merchant offers {value} gold instead of {RealValue} -- the rubies in the pouch are not counted");
        }

        Out.Note("Refactor task: a common IInventoryEntry interface with Weight and Value; "
                 + "Item answers directly, Bag asks its children. No type checks.");
    }
}
