# GoF / Behavioral / Chain of Responsibility

## Scenario

When a monster hits a *Dungeon Raid* hero, the damage passes through a series of
defenses:

1. **Invulnerability potion**: stops the hit completely
2. **Magic shield**: absorbs what it can
3. **Armor**: reduces the rest by a flat amount
4. **HP**: takes whatever is left

## Smell

`DamageResolver.resolve()` does all the steps in one method:

- the order is fixed in the code, and steps cannot be reused or tested separately
- a new defense (dodge, block, damage reflection) means editing this method, and
  putting it in the right place
- the potion was added at the bottom, next to the HP code

## Consequence in the demo

A normal hit works. But with the potion active, HP stays at 100 while the **shield
drops from 20 to 0**. An invulnerable hero should not lose anything.

## Your task (live)

Make each step an object that handles its part and passes the rest on:

```java
public abstract class DamageHandler {
    private DamageHandler next;

    public DamageHandler linkWith(DamageHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Hero hero, int damage);

    protected void passOn(Hero hero, int damage) {
        if (next != null) {
            next.handle(hero, damage);
        }
    }
}
```

Add `InvulnerabilityHandler`, `ShieldHandler`, `ArmorHandler` and `HealthHandler`,
then assemble them with the potion first.

## Hints

- Each handler decides two things: what to do, and **whether to pass the hit on**.
  `InvulnerabilityHandler` stops the chain just by not calling `passOn()`.
- The chain is put together at **runtime**. A ghost enemy that ignores armor uses
  a chain without `ArmorHandler`.
- A new defense is one class plus one link. Nothing else changes.
- Decide what happens at the end of the chain: an unhandled request should not
  disappear silently.
- You know this pattern already: servlet filters, Spring Security filters,
  middleware.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/chainofresponsibility
```
