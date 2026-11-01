# GoF / Behavioral / Command

## Scenario

*Dungeon Raid* has a turn-based mode. The player plans a turn with the keyboard:
`A` / `D` move the hero, `Q` drinks a potion. Until they end the turn, they can
press `Z` to take back actions, one at a time.

## Smell

`InputHandler.press()` calls the hero directly from a `switch`. The action is
gone once the method returns:

- undo is a **guess** based on the last key, and it covers only moves
- there is no history, so only one step could ever be undone
- keys are hard-coded, so they cannot be rebound
- a replay or a recorded macro is impossible

## Consequence in the demo

The player presses `D`, `D`, `Q`, then `Z` three times. They should be back where
they started, but the hero is still on tile **2**, with the potion **gone**.

## Your task (live)

Turn an action into an object:

```java
public interface Command {
    void execute();
    void undo();
}

public class MoveCommand implements Command { ... }
public class DrinkPotionCommand implements Command { ... }
```

`InputHandler` gets two collections:

- `Map<String, Supplier<Command>> bindings`: which key creates which command
- `Deque<Command> history`: what has been executed, newest first

`press(key)` creates the command, executes it and pushes it. `Z` pops one and calls
`undo()`. Add `bind(key, command)` so that keys can be rebound.

## Hints

- A command has to **remember what it needs to undo**. A potion heals less near
  full HP, so `DrinkPotionCommand` saves the HP from before the drink instead of
  subtracting 30.
- The handler knows nothing about heroes or potions any more, only `Command`.
- Once actions are objects, you get undo, replay, macros, queues and network
  sync almost for free.
- You know this pattern already: `Runnable`, `Callable` and task queues, though
  those have no `undo()`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/command
```
