# GoF / Structural / Bridge

## Scenario

A *Dungeon Raid* spell combines two independent things:

- a **form**, which decides who gets hit: Bolt (one enemy), Nova (the whole room)
- an **element**, which decides what happens to them: Fire burns, Ice slows,
  Poison poisons

Poison is the newest element.

## Smell

Each combination is its own class: `FireBolt`, `FireNova`, `IceBolt`, ... Two
ideas that change independently were folded into one hierarchy, so the count
**multiplies**: 2 forms × 3 elements = 6 classes, and one more of each makes 12.
The targets and the effect are copied into every class.

## Consequence in the demo

- `IceNova` was copied from `FireNova`, so it **burns** instead of slowing.
- **Poison Nova does not exist**, because nobody wrote that class.

## Your task (live)

Split the hierarchy in two and connect them with a field (the bridge):

```csharp
public interface IElement
{
    string Name { get; }
    string Effect { get; }
}

public abstract class Spell(IElement element)   // the bridge
{
    public abstract string Form { get; }
    public abstract string Targets { get; }
}

public sealed class Nova(IElement element) : Spell(element) { ... }

Spell spell = new Nova(new Ice());
```

`SpellBook` builds every combination in a loop.

## Hints

- Ask: **which two things vary independently?** If you can say "X and Y" about
  your class names, you may need a Bridge.
- The count now **adds up**: 2 + 3 = 5 classes for 6 spells, and 3 + 4 = 7 for 12.
- A new form or element is one class. It works with everything on the other side.
- Bridge vs Strategy: the code looks the same. Bridge is about structure, planned
  up front to keep two hierarchies apart. Strategy is about swapping an algorithm.
- You know this pattern already: ADO.NET. Your code uses `DbConnection`, and the
  provider (SQL Server, PostgreSQL, SQLite) is the separate implementation hierarchy.

## C# specifics

- **Primary constructors (C# 12)** shrink each side of the bridge:
  `public sealed class Nova(IElement element) : Spell(element)` is the whole
  constructor, and `Spell` reads the captured `element` directly.
- **A compile-time bridge with generics.** `Wave<TElement> where TElement : IElement, new()`
  takes the element as a type argument: `new Wave<Poison>()`. .NET generics are
  not erased (unlike Java), so `TElement` can be created with `new()` and is
  still known at run time. The cost: the element is fixed at compile time, so the
  constructor version is still needed when combinations come from data.
- **Bridges already in .NET:**
  - ADO.NET: `DbConnection` / `DbCommand` on one side, providers
    (`SqlConnection`, `NpgsqlConnection`, `SqliteConnection`) on the other
  - `Stream` (file, memory, network, gzip) on one side, `StreamReader` /
    `BinaryReader` / `XmlReader` on the other: any reader works over any stream
  - `ILogger` used by your code, `ILoggerProvider` (console, file, Seq) doing the work

## Solution

```bash
git diff bad-practice-principles-csharp best-practice-principles-csharp -- src/DesignPatterns/Gof/Structural/Bridge
```
