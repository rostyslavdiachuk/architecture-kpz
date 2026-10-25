# GoF / Creational / Singleton

## Scenario

*Dungeon Raid* boots three subsystems: the options menu, the audio engine and
the enemy spawner. The player opens the options menu, sets the difficulty to
**HARD**, mutes the music and walks into the first dungeon.

## Smell

`GameSettings` stands for something there is exactly **one** of — the player's
options, backed by one `settings.cfg` — but it has a public constructor. Each
subsystem does `new GameSettings()` and keeps its own private copy:

- `OptionsMenu` changes **its** copy
- `AudioEngine` reads **its** copy
- `EnemySpawner` reads **its** copy

Nothing in the code says "there is only one", so nothing enforces it.

## Consequence in the demo

- The player muted the music, but the dungeon theme still plays at volume `80`.
- The player picked HARD, but goblins spawn with NORMAL HP (`40` instead of `60`).
- `settings.cfg` is parsed **3 times** for a single game.

## Your task (live)

Turn `GameSettings` into a Singleton:

```java
public final class GameSettings {
    private GameSettings() { /* parse settings.cfg once */ }

    private static final class Holder {
        private static final GameSettings INSTANCE = new GameSettings();
    }

    public static GameSettings getInstance() {
        return Holder.INSTANCE;
    }
}
```

Then change the three subsystems to use `GameSettings.getInstance()` instead of
`new GameSettings()`.

## Hints

- The **private constructor** is the part that matters: it makes a second
  instance impossible to create, not just "discouraged".
- The *holder idiom* is lazy (the instance is created on the first
  `getInstance()` call) and thread-safe, because the JVM initializes a class only
  once. No `synchronized`, no double-checked locking.
- `enum GameSettings { INSTANCE; ... }` is another valid variant. It is also safe
  against reflection and serialization.

## Discussion: the price of a Singleton

A Singleton is **global state**. Anyone can reach it, so dependencies are hidden
and unit tests leak state into each other. A common alternative is to create one
instance in `main` and **pass it in** through constructors (see `solid/dip`).
That gives you "only one" by convention and keeps classes testable. Use a real
Singleton only when "only one" must be guaranteed by the code itself.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/singleton
```
