# GoF / Behavioral / Mediator

## Scenario

A *Dungeon Raid* party fights a boss. Before each fire breath, whoever spots the
windup warns the party, and everyone has to step out of the fire. The party was
Tank, Healer and Mage. The last patch added the **Rogue**.

## Smell

Each member holds direct references to the others and has its own `warnParty()`,
which names them one by one:

- four members already mean **9 references** to wire, and a full mesh needs 12
  (20 with five members)
- adding a member means editing every other class
- the classes cannot be reused or tested apart from each other

The Rogue's author wrote the new class, but did not edit the three old ones.

## Consequence in the demo

The tank warns the party. Tank, Healer and Mage step out. **The Rogue burns**,
because the code that sends the warning does not know the Rogue exists.

## Your task (live)

Put a single object in the middle:

```java
public class Raid {
    private final List<PartyMember> members = new ArrayList<>();

    public void join(PartyMember member) { ... }
    public void warnParty(PartyMember from) { /* everyone moves out */ }
}
```

- Each member gets only a `Raid` reference and calls `raid.warnParty(this)`.
- Delete the `meet(...)` methods and the references between members.
- Tank, Healer, Mage and Rogue no longer know that the others exist.

## Hints

- Many-to-many links become **many-to-one**: N links, not N × (N - 1).
- A new member is one class and one `raid.join(...)` call.
- Coordination rules now live in one place: "the Healer's warning goes out only if
  the Healer is alive" is a single `if` in `Raid`.
- Watch out: the mediator easily grows into a **god object**. Keep it about
  coordination, not game logic.
- Mediator vs Observer: an observer's subject doesn't care who listens or what they
  do. A mediator knows its colleagues and decides what happens.
- You know this pattern already: an air traffic controller, a chat room, a UI
  dialog coordinating its widgets.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/mediator
```
