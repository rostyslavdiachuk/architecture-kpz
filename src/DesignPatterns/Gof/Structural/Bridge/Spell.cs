namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>
/// Bridge: the form of a spell and its element vary independently, so they live in
/// two hierarchies, connected by the <c>element</c> reference.
/// </summary>
/// <remarks>
/// A new form or a new element is one class, and it combines with everything on
/// the other side. C# 12 primary constructors keep each form down to a few lines:
/// the element is captured once and passed to the base class.
/// </remarks>
public abstract class Spell(IElement element)
{
    public abstract string Form { get; }

    public abstract string Targets { get; }

    public string Element => element.Name;

    public string Effect => element.Effect;

    public string Cast() => $"{Element} {Form} hits {Targets} and {Effect} them";
}

public sealed class Bolt(IElement element) : Spell(element)
{
    public override string Form => "Bolt";
    public override string Targets => "one enemy";
}

public sealed class Nova(IElement element) : Spell(element)
{
    public override string Form => "Nova";
    public override string Targets => "the whole room";
}
