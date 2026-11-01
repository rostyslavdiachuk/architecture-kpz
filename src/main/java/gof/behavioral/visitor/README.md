# GoF / Behavioral / Visitor

## Scenario

A *Dungeon Raid* level holds **monsters**, **chests**, **traps** and, since the last
patch, **shrines**. The game runs several operations over them: calculating the
**score** and drawing the **minimap** now, with saving to a file and map export
coming next. New object types are rare, but new operations keep coming.

## Smell

Each operation is its own chain of `instanceof` checks:

- the same type dispatch is repeated in each operation
- the compiler says nothing when a new type appears, since the `else` branch or
  the missing branch swallows it
- moving the operations into the objects themselves would fill them with unrelated
  code: a `Monster` should not know about file formats

## Consequence in the demo

The shrine was added, and neither operation was told:

- the score is **120** instead of 170
- the minimap shows **`M$^?`** instead of `M$^S`

## Your task (live)

One visitor interface, with one method per type:

```java
public interface MapObjectVisitor<R> {
    R visitMonster(Monster monster);
    R visitChest(Chest chest);
    R visitTrap(Trap trap);
    R visitShrine(Shrine shrine);
}

public interface MapObject {
    <R> R accept(MapObjectVisitor<R> visitor);
}

record Chest(int gold) implements MapObject {
    public <R> R accept(MapObjectVisitor<R> visitor) { return visitor.visitChest(this); }
}
```

`ScoreVisitor` and `MinimapVisitor` replace the two classes with `instanceof`.

## Hints

- `accept` is **double dispatch**: the object picks the method, the visitor picks the
  operation.
- A new operation is one new class, and the objects don't change.
- A new object type means one new method in the interface, and the **compiler then
  points at each visitor** you have to update. That is the bug from the demo, caught.
- So use Visitor when types are **stable** and operations **change**. If it's the
  other way round, plain polymorphic methods are the better fit.
- In modern Java, a **`sealed` interface with an exhaustive `switch`** gives the same
  compile-time check without `accept`:
  ```java
  sealed interface MapObject permits Monster, Chest, Trap, Shrine {}

  int points = switch (object) {
      case Monster m -> m.xp();
      case Chest c   -> c.gold();
      case Trap t    -> 0;
      case Shrine s  -> s.blessing();
  };
  ```

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/visitor
```
