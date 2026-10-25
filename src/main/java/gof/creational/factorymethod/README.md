# GoF / Creational / Factory Method

## Scenario

*Dungeon Raid* has levels in different biomes: **Ice**, **Fire** and the newly
added **Swamp**. Each level spawns a wave of enemies, and later a
reinforcement. Every enemy must belong to the level's biome.

## Smell

`DungeonLevel` takes the biome as a `String` and decides which class to `new`
with a `switch`, and the same `switch` is copied into two methods:

- `spawnWave()` knows Ice, Fire and Swamp
- `spawnReinforcement()` was copied before Swamp existed and knows only Ice and Fire

Adding a biome means finding every `switch` by hand. The `default -> new
Goblin()` branch hides the one you missed.

## Consequence in the demo

Ice and Fire levels are fine. In the **Swamp** level the wave is three Swamp
Trolls, but the reinforcement is a **Goblin**, a cave monster in a swamp.

## Your task (live)

Let subclasses decide what to create:

```java
public abstract class DungeonLevel {
    public abstract String biome();
    protected abstract Enemy createEnemy();   // the factory method

    public List<Enemy> spawnWave(int size) { /* calls createEnemy() */ }
    public Enemy spawnReinforcement()      { return createEnemy(); }
}

public class SwampLevel extends DungeonLevel {
    protected Enemy createEnemy() { return new SwampTroll(); }
}
```

Add `IceCaveLevel`, `FireVolcanoLevel` and `SwampLevel`. Delete the `switch`es
and the `Goblin` fallback.

## Hints

- The base class owns the **algorithm** (spawn a wave, send reinforcements). The
  subclass only answers one question: *which* object?
- Adding a new biome now means adding **one class**, and since `createEnemy()`
  is abstract, the compiler will not let you forget it.
- Factory Method uses **inheritance**: one creation method, overridden per
  subclass. Compare it with Abstract Factory, the next lesson.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/factorymethod
```
