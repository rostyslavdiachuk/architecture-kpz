# GoF / Behavioral / Memento

## Scenario

*Dungeon Raid* saves the game when the hero rests at a campfire. If the hero then
loses to the boss, the player reloads and should be back exactly as they were at
the campfire.

## Smell

`Checkpoint` reads and writes the hero's fields **from the outside**:

- `Hero` has to expose setters and its live inventory list, only so that it can
  be saved. Now anyone can change them.
- `Checkpoint` has to know every field of `Hero`. A new field is easily forgotten.
- `hero.inventory()` returns the list itself, so the checkpoint saves a
  **reference**, not a copy.

## Consequence in the demo

After the reload, HP and gold are correct, but:

- the **potion is gone**, because the checkpoint's list is the hero's live list
- **xp stays at 300**, because `xp` was added after `Checkpoint` was written

## Your task (live)

Let the hero save itself:

```java
public class Hero {
    public static final class Memento {          // only Hero can read it
        private final int hp;
        private final int gold;
        private final int xp;
        private final List<String> inventory;    // a copy
        private Memento(Hero hero) { ... }
    }

    public Memento save() { return new Memento(this); }
    public void restore(Memento memento) { ... }
}
```

Replace `Checkpoint` with a caretaker, `Campfire`, that stores mementos and never
looks inside them. Then delete the setters.

## Hints

The pattern has three roles:

| Role | Here | Knows |
|---|---|---|
| **Originator** | `Hero` | how to save and restore itself |
| **Memento** | `Hero.Memento` | the frozen state; opaque to everyone else |
| **Caretaker** | `Campfire` | when to save and restore, but not what is inside |

- `Memento` is nested, so `Hero` can read its private fields and nobody else can.
- **Deep-copy the mutable parts**, both when saving and when restoring. Otherwise
  the next fight changes the save, even after a reload.
- A new `Hero` field needs only a change inside `Hero`.
- Saving everything can be expensive. Many games store only the differences.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/memento
```
