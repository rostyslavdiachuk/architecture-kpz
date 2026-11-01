# GoF / Structural / Composite

## Scenario

A *Dungeon Raid* hero carries items and bags, and bags can hold other bags. The
game needs two totals: the **weight** (over 15 slows the hero down) and the
**value** (what a merchant pays).

```
Sword (8)
Backpack (2)
├── Potion (1)
└── Pouch (1)
    ├── Ruby (1)
    ├── Ruby (1)
    └── Gold Bar (5)
```

## Smell

`Item` and `Bag` have nothing in common, so collections hold `Object`, and
`Inventory` does the walking with `instanceof`:

- every operation repeats the same walk
- each walk has a fixed depth, written when a bag could hold only items
- `add(Object)` accepts anything at all

## Consequence in the demo

- Weight comes out as **11** instead of 19: the hero is overloaded but runs at
  full speed.
- Value comes out as **150** instead of 1950: the pouch of treasure sells for
  nothing.

## Your task (live)

Give the leaf and the container one interface, and let the tree do the walking:

```java
public interface InventoryEntry {
    int weight();
    int value();
}

public record Item(String name, int weight, int value) implements InventoryEntry {}

public class Bag implements InventoryEntry {
    public int weight() { /* own weight + sum of children's weight() */ }
    public int value()  { /* sum of children's value() */ }
}
```

Delete `Inventory`: the hero's inventory is simply a `Bag`.

## Hints

- The client treats one item and a whole tree **the same way**.
- Recursion happens by itself: a `Bag` calls `weight()` on its children and does
  not care what they are, so any depth works.
- Use it when your data is really a **part-whole tree**. Files and folders, UI
  panels and buttons, and org charts have the same shape.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/composite
```
