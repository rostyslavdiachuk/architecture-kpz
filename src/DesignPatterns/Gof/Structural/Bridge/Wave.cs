namespace DesignPatterns.Gof.Structural.Bridge;

/// <summary>
/// C# specific: a compile-time bridge. The element is a type argument instead of a
/// constructor argument; the <c>new()</c> constraint lets the spell create it.
/// </summary>
/// <remarks>
/// <para>Unlike Java, .NET generics are not erased: <c>Wave&lt;Fire&gt;</c> and
/// <c>Wave&lt;Ice&gt;</c> are distinct types at run time, so <typeparamref name="TElement"/>
/// can be instantiated and its name read back.</para>
/// <para>Trade-off: the element is fixed when the code is compiled. That fits when the
/// combination is known up front; a constructor argument (<see cref="Spell"/>) is needed
/// when it comes from data, like the loop in <see cref="SpellBook"/>.</para>
/// </remarks>
public sealed class Wave<TElement>() : Spell(new TElement())
    where TElement : IElement, new()
{
    public override string Form => "Wave";
    public override string Targets => "a line of enemies";
}
