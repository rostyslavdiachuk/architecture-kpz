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

```java
public interface Element {
    String name();
    String effect();
}

public abstract class Spell {
    protected final Element element;   // the bridge
    public abstract String form();
    public abstract String targets();
}

public class Nova extends Spell { ... }

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
- You know this pattern already: JDBC. Your code uses `Connection`, and the driver
  is the separate implementation hierarchy.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/bridge
```
