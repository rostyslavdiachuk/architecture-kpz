namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>One side of the bridge: what a spell does to whoever it hits.</summary>
public interface IElement
{
    string Name { get; }

    string Effect { get; }
}

public sealed class Fire : IElement
{
    public string Name => "Fire";
    public string Effect => "burns";
}

public sealed class Ice : IElement
{
    public string Name => "Ice";
    public string Effect => "slows";
}

public sealed class Poison : IElement
{
    public string Name => "Poison";
    public string Effect => "poisons";
}
