# GoF / Structural / Flyweight

## Scenario

A *Dungeon Raid* map is a grid of **200 × 200** tiles. There are only **3 kinds**
of tile: wall, floor and water. Each kind has a 64 KB texture and a "walkable"
flag.

## Smell

Every `Tile` stores its own copy of everything: position, kind, texture and flag.
Yet **only the position** differs between two floor tiles. The rest is the same
data, repeated 40,000 times.

## Consequence in the demo

**40,000** textures are decoded for 3 kinds of tile: **2500 MB** of memory, which
a 200 × 200 map does not need.

## Your task (live)

Separate what is shared from what is unique:

| | Where | Example |
|---|---|---|
| **Intrinsic** (shared) state | one `TileType` per kind | kind, texture, walkable |
| **Extrinsic** (unique) state | each `Tile` | X, Y |

```csharp
public sealed record TileType(string Kind, Texture Texture, bool Walkable);

public class TileTypes                          // the flyweight factory
{
    private readonly Dictionary<string, TileType> _cache = [];
    public TileType Get(string kind) { /* create once, then reuse */ }
}

public readonly record struct Tile(int X, int Y, TileType Type);
```

After the refactor there are still 40,000 `Tile`s, but **3** textures (192 KB).

## Hints

- The flyweight **must be immutable**. It is shared, so a change to one floor
  tile would change all of them. A positional `record` gives you that for free.
- Worth it only when you have **a lot** of objects and most of their state repeats.
- Flyweight vs Object Pool: flyweights are **shared at the same time** by many
  users. A pooled object has **one user at a time** and comes back to be reused.
- Flyweight vs Singleton: there is one flyweight **per kind**, not one in total.
- You know this pattern already: string literals are interned, so two equal
  literals are the same object.

## Solution

```bash
git diff bad-practice-principles-csharp best-practice-principles-csharp -- src/DesignPatterns/Gof/Structural/Flyweight
```
