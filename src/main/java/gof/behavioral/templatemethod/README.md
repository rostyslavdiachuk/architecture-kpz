# GoF / Behavioral / Template Method

## Scenario

A *Dungeon Raid* fighter's turn in combat always goes the same way:

1. **stunned?** Skip the turn.
2. **poisoned?** Lose 5 HP.
3. **act**: this is the only step that differs. The Warrior swings an axe, the Mage
   casts a fireball, the Rogue stabs.
4. **after the action**: the Mage also regains 10 mana.

## Smell

`Warrior`, `Mage` and `Rogue` each implement the whole `takeTurn()`, so the shared
rules exist three times:

- a new rule means editing each class
- nothing guarantees that the copies agree
- a new class of fighter is a new chance to get the rules wrong

## Consequence in the demo

- The **Mage** does not lose HP to poison. Its copy is older than the poison rule.
- The **Rogue** acts while stunned. Its stun check got lost in a merge.

Only the Warrior follows all the rules.

## Your task (live)

Move the skeleton into the base class, and leave only the differences below:

```java
public abstract class Fighter {

    public final String takeTurn() {           // the template method
        if (stunned) { ... return "skips the turn"; }
        if (poisoned) { hp -= POISON_DAMAGE; }
        String action = act();
        afterAct();
        return action;
    }

    protected abstract String act();           // a step subclasses must write
    protected void afterAct() { }              // a hook they may override
}
```

`Mage` overrides `afterAct()` to regain mana.

## Hints

- **`final`** matters: subclasses must not be able to change the rules.
- *Abstract* steps must be implemented, while *hooks* have a default and are
  optional.
- This is the **Hollywood principle**: "don't call us, we'll call you". The base
  class calls the subclass, not the other way round.
- Downside: it relies on inheritance, so a subclass is tied to its base class. If
  you need to swap steps at runtime, look at Strategy.
- You know this pattern already: `AbstractList`, `HttpServlet.service()` calling
  `doGet()`, JUnit's `@BeforeEach` / test / `@AfterEach`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/templatemethod
```
