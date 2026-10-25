# GoF / Creational / Abstract Factory

## Scenario

Every *Dungeon Raid* dungeon is built from three parts: **walls**, a
**monster** and a **treasure chest**. The game has two themes, and the parts
of one theme belong together:

| Theme | Wall | Monster | Treasure |
|---|---|---|---|
| Ice | ice blocks | Ice Golem | Frost Sword |
| Fire | lava rock | Fire Imp | Flame Bow |

## Smell

There is one generator class per theme, and each picks its parts by hand with
`new`. `FireDungeonGenerator` was **copy-pasted** from `IceDungeonGenerator`,
and only two of the three `new`s were changed. Nothing in the code says "these
three classes form a family", so nothing stops you from mixing them.

## Consequence in the demo

The Ice dungeon is fine. The Fire dungeon has lava walls and a Fire Imp, but its
chest holds a **Frost Sword**: an ice item in the fire world.

## Your task (live)

1. Create an interface for the **whole family**:
   ```java
   public interface DungeonThemeFactory {
       Wall createWall();
       Monster createMonster();
       Treasure createTreasure();
   }
   ```
2. Implement it twice: `IceThemeFactory` and `FireThemeFactory`.
3. Replace both generators with **one** `DungeonGenerator` that receives a
   factory and only calls `factory.createXxx()`.

## Hints

- The client (`DungeonGenerator`) never names a concrete class again, so it
  **cannot** mix themes. Pick the factory once and every part matches.
- A new theme (say, Swamp) is one new factory class. The generator does not
  change.
- Factory Method vs Abstract Factory: Factory Method is **one** method a subclass
  overrides. Abstract Factory is an **object** with several creation methods
  that you pass around.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/abstractfactory
```
