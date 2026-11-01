# GoF / Structural / Facade

## Scenario

Starting a *Dungeon Raid* level takes five subsystems, in a strict order: load
the assets → generate the world → spawn the enemies → start the music → show the
HUD. Two buttons start a level: **New game** and **Continue**.

## Smell

Each button holds references to the subsystems and calls them one by one:

- the UI has to know how the engine works inside
- the startup sequence exists in two places, with nothing keeping them in sync
- a sixth subsystem means finding every button that starts a level

## Consequence in the demo

- **New game** works: 24 enemies, music playing.
- **Continue** was written from memory: enemies are spawned before the world
  exists, so the level is **empty**, and it is **silent**.

## Your task (live)

Put one simple entry point in front of the subsystems:

```java
public class GameSession {
    private final AssetLoader assets = new AssetLoader();
    // ... the other subsystems

    public void startLevel(String level) { /* the one correct sequence */ }
}
```

`MainMenu` and `ContinueButton` receive a `GameSession` and call only
`startLevel(...)`.

## Hints

- A facade **adds no new abilities**. It only makes existing ones easy to use.
- The subsystems stay available. A level editor may still call `WorldGenerator`
  directly.
- Don't let the facade grow into a god object. If it starts holding game logic,
  split it.
- Facade vs Adapter: an adapter makes an interface **compatible**, a facade makes
  a subsystem **simple**.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/facade
```
