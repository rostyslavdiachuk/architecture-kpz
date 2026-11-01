# GoF / Behavioral / Observer

## Scenario

When a *Dungeon Raid* enemy dies, several parts of the game react: the **HUD**
counts the kill, the **quest log** checks its quests, and the **audio engine**
plays a sound. A boss gets a fanfare instead of the regular death sound.

## Smell

`Enemy.die()` calls every interested system by name:

- a monster has to know about the HUD, quests and audio, and takes them all in its
  constructor
- `Boss` overrides `die()` with a copy of the list
- the next system (achievements, statistics, loot) means editing `Enemy`, `Boss`
  and every place that creates them

`Boss.die()` was copied before the quest log existed.

## Consequence in the demo

The goblin's quest completes. The hero kills the dragon, and the HUD counts it, but
the quest **"Slay the Dragon" stays open**. The main quest of the game cannot be
finished.

## Your task (live)

Invert the dependency: the enemy announces, and the others listen.

```java
public interface DeathListener {
    void onEnemyDied(Enemy enemy);
}

public class DeathEvents {                  // the subject
    public void subscribe(DeathListener listener) { ... }
    public void publish(Enemy enemy) { ... }
}
```

- `Enemy` receives only `DeathEvents`, and `die()` becomes `events.publish(this)`.
- `Hud`, `QuestLog` and `AudioEngine` implement `DeathListener` and subscribe once
  at startup.
- `Boss` no longer overrides `die()`. The audio engine picks the fanfare by
  checking `enemy.isBoss()`.
- Prove it: add `Achievements` **without touching** `Enemy`.

## Hints

- The publisher does not know who is listening, or how many listeners there are.
- Listener lists can leak memory: **unsubscribe** an object you no longer need,
  or it stays alive because the subject holds on to it.
- Don't rely on the order of notification. If order matters, you probably need
  something else.
- One listener throwing an exception should not silently stop the rest.
- You know this pattern already: `addActionListener` in Swing, DOM events,
  `@EventListener` in Spring.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/observer
```
