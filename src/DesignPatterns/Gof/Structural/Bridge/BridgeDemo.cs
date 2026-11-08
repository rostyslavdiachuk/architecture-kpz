using DesignPatterns.Common;

namespace DesignPatterns.Gof.Structural.Bridge;

public static class BridgeDemo
{
    private static readonly Dictionary<string, string> Effects = new()
    {
        ["Fire"] = "burns",
        ["Ice"] = "slows",
        ["Poison"] = "poisons",
    };

    public static void Run()
    {
        Out.Header("GoF / Bridge -- one class per element and form combination");

        var book = new SpellBook();

        foreach (var element in new[] { "Fire", "Ice", "Poison" })
        {
            foreach (var form in new[] { "Bolt", "Nova" })
            {
                var spell = book.Find(element, form);
                if (spell is null)
                {
                    Out.Fail($"{element} {form}: there is no such class -- the spell cannot be cast");
                }
                else if (spell.Effect != Effects[element])
                {
                    Out.Fail($"{spell.Cast()} -- that is not what {element} does");
                }
                else
                {
                    Out.Ok(spell.Cast());
                }
            }
        }

        Out.Note("Refactor task: split into two hierarchies: Spell (Bolt, Nova) holds an "
                 + "IElement (Fire, Ice, Poison). 2 forms + 3 elements = 5 classes, 6 spells.");
    }
}
