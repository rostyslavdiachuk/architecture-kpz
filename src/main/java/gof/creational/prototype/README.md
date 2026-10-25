# GoF / Creational / Prototype

## Scenario

The wave spawner of *Dungeon Raid* fills a room with 20 goblins. Before that,
the level designer built a boss in the editor at runtime: the **Frost Goblin
King**, a goblin with 3× HP, 60% ice resistance and the extra ability
`Blizzard`. The boss room must spawn exactly that enemy.

## Smell

`EnemySpawner.spawn(String type)` builds every enemy **from scratch** inside a
`switch`:

- every call loads the mesh from disk again (`AssetLoader.loadMesh`), although
  all goblins share the same model
- stats and abilities are hard-coded in the `case`
- only the kinds listed in the `switch` exist. A new variant means editing the
  spawner, and a variant made at runtime cannot be spawned at all

## Consequence in the demo

- 20 goblins → `goblin.mesh` is read **20 times** (~2000 ms, the wave freezes
  the game).
- `spawn("Frost Goblin King")` hits `default` and returns a **plain goblin**. The
  designer's work is lost.

## Your task (live)

Let an enemy **copy itself**, and spawn by copying ready-made prototypes:

1. Add a copy constructor and `Enemy copy()`:
   - **deep-copy** the mutable parts: `Stats` and the `abilities` list
   - **share** the immutable `Mesh`. That is why the disk is read only once
2. Replace `EnemySpawner` with `EnemyRegistry`:
   ```java
   registry.register("Goblin", goblinPrototype);
   registry.register("Frost Goblin King", frostKing);   // built at runtime!
   Enemy e = registry.spawn("Frost Goblin King");       // = prototype.copy()
   ```

## Hints

- The client should not need to know the concrete class or how to build the
  object. It only knows a prototype it can copy.
- **Shallow copy is the classic Prototype bug.** If the clone shares the
  original's `Stats`, hitting one goblin hurts the whole wave. The demo checks
  this: one goblin takes 30 damage and the other 19 must keep full HP.
- Prefer a copy constructor / `copy()` method over `Cloneable` +
  `Object.clone()`. `Cloneable` has no `clone()` method of its own, `clone()`
  is shallow by default, and it bypasses constructors (Effective Java, item 13).

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/prototype
```
