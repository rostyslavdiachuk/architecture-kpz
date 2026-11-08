namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>All the spells the game knows: every form combined with every element.</summary>
public class SpellBook
{
    private readonly List<Spell> _spells = [];

    public SpellBook()
    {
        IElement[] elements = [new Fire(), new Ice(), new Poison()];
        foreach (var element in elements)
        {
            _spells.Add(new Bolt(element));
            _spells.Add(new Nova(element));
        }
    }

    public Spell? Find(string element, string form) =>
        _spells.FirstOrDefault(spell => spell.Element == element && spell.Form == form);
}
