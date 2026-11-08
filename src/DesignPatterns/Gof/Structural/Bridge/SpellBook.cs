namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>All the spells the game knows. Every spell has to be a class of its own.</summary>
public class SpellBook
{
    private readonly List<ISpell> _spells =
    [
        new FireBolt(), new FireNova(),
        new IceBolt(), new IceNova(),
        new PoisonBolt(),
    ];

    public ISpell? Find(string element, string form) =>
        _spells.FirstOrDefault(spell => spell.Element == element && spell.Form == form);
}
