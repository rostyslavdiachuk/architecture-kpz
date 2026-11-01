# GoF / Behavioral / Iterator

## Scenario

A *Dungeon Raid* level is a graph of rooms, and its corridors form loops:

```
Entrance -- Hall -- Armory
             |        |
             +------ Crypt -- Vault
```

The **minimap** lists the rooms from nearest to farthest. The **quest log** sums
up the gold on the level, which is **460**.

## Smell

`Dungeon.exits()` returns its internal `Map<String, List<String>>`, so every client
writes its own traversal:

- the clients know how the rooms are stored, and changing it breaks them all
- the traversal is copied, and each copy must handle loops correctly
- `GoldCounter` has no "visited" set. Its first version hung the game, and the fix
  was a step limit

## Consequence in the demo

The minimap is right, because its author got it right. The quest log reports a
wildly wrong amount of gold: rooms on a loop are counted over and over until the
step limit runs out.

## Your task (live)

Move the traversal into the collection, and stop exposing the map:

```java
public class Dungeon {
    public Iterable<String> breadthFirst(String start) { ... }
    public Iterable<String> depthFirst(String start)   { ... }
}
```

The clients shrink to:

```java
for (String room : dungeon.breadthFirst("Entrance")) {
    total += dungeon.goldIn(room);
}
```

Delete `exits()`.

## Hints

- An iterator remembers **where it is**, and each one has its own state, so two
  walks can run at the same time.
- Implement `Iterable`, and the Java `for-each` loop works for free.
- One collection can offer **several traversal orders** without its clients
  knowing how any of them work.
- The storage can now change, say to an adjacency matrix, without touching a client.
- You know this pattern already: every `java.util` collection, and in spirit
  `Stream`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/iterator
```
