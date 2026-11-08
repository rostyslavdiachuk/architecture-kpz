namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>
/// Bridge smell: two independent ideas are folded into one class hierarchy.
/// </summary>
/// <remarks>
/// A spell has a <b>form</b> (Bolt, Nova) and an <b>element</b> (Fire, Ice,
/// Poison). Here each combination is a class, so forms x elements classes are
/// needed, and the targets and the effect are copied into each of them.
/// </remarks>
public interface ISpell
{
    string Element { get; }

    string Form { get; }

    string Targets { get; }

    string Effect { get; }

    // C# 8+ default interface method, the counterpart of a Java default method.
    string Cast() => $"{Element} {Form} hits {Targets} and {Effect} them";
}

internal sealed class FireBolt : ISpell
{
    public string Element => "Fire";
    public string Form => "Bolt";
    public string Targets => "one enemy";
    public string Effect => "burns";
}

internal sealed class FireNova : ISpell
{
    public string Element => "Fire";
    public string Form => "Nova";
    public string Targets => "the whole room";
    public string Effect => "burns";
}

internal sealed class IceBolt : ISpell
{
    public string Element => "Ice";
    public string Form => "Bolt";
    public string Targets => "one enemy";
    public string Effect => "slows";
}

internal sealed class IceNova : ISpell
{
    public string Element => "Ice";
    public string Form => "Nova";
    public string Targets => "the whole room";
    public string Effect => "burns"; // copy-pasted from FireNova
}

internal sealed class PoisonBolt : ISpell
{
    public string Element => "Poison";
    public string Form => "Bolt";
    public string Targets => "one enemy";
    public string Effect => "poisons";
}

// PoisonNova: the new element shipped without it
