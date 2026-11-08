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
        Out.Header("GoF / Bridge -- spell forms and elements are two hierarchies");

        var book = new SpellBook();

        foreach (var element in new[] { "Fire", "Ice", "Poison" })
        {
            foreach (var form in new[] { "Bolt", "Nova" })
            {
                var spell = book.Find(element, form);
                if (spell is null)
                {
                    Out.Fail($"{element} {form}: the spell cannot be cast");
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

        Out.Note("2 forms + 3 elements = 5 classes, 6 spells. A new form or element is one class, "
                 + "and it combines with everything on the other side.");

        Out.Step("C# extra: a compile-time bridge -- the element as a generic type argument");
        Spell wave = new Wave<Poison>();
        Out.Ok($"{wave.Cast()}  (runtime type: Wave<{wave.GetType().GetGenericArguments()[0].Name}>)");
        Out.Note("Wave<Poison> is fixed when compiled; use the constructor form when the element comes from data.");
    }
}
