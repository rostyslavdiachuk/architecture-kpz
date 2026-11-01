# GoF / Behavioral / Strategy

## Scenario

*Dungeon Raid* enemies differ in how they fight. An **aggressive** Orc charges the
hero. A **kiting** Archer keeps their distance and shoots. The level designer has
just added a **summoner** Necromancer to the level file, and also wants wounded
goblins to start **fleeing** mid-fight.

## Smell

`Enemy.act()` holds all the AI algorithms in one `switch` on a string:

- a new AI means editing `Enemy`, so the class grows with each behavior
- the algorithms cannot be tested or reused separately
- the AI is fixed at creation and cannot change during a fight
- `default` hides unknown names

## Consequence in the demo

The Orc and the Archer are right. The Necromancer **charges into melee** instead of
summoning skeletons: nobody added a `case`, and the `default` branch hid it.
Fleeing goblins are not possible at all.

## Your task (live)

Pull each algorithm out into its own class:

```java
public interface AiStrategy {
    String act(int distanceToHero);
}

public class Enemy {
    private AiStrategy ai;

    public String act(int distance) { return ai.act(distance); }
    public void setAi(AiStrategy ai) { this.ai = ai; }
}
```

Add `Aggressive`, `Kiting`, `Summoner` and `Fleeing`. Prove the runtime swap:
`goblin.setAi(new Fleeing())` when the goblin is wounded.

## Hints

- `Enemy` stays closed for modification: a new AI is one new class (OCP in action).
- A strategy with one method is a **functional interface**, so a small one can be a
  lambda: `new Enemy("Bat", distance -> "flutters around")`.
- The **client chooses** the strategy, and the enemy does not care which one it has.
- Strategy vs Template Method: Strategy uses **composition** and swaps the whole
  algorithm at runtime. Template Method uses **inheritance** and fixes the skeleton.
- You know this pattern already: `Comparator` in `list.sort(...)`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/strategy
```
