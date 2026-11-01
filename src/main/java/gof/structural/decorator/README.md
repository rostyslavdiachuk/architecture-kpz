# GoF / Structural / Decorator

## Scenario

The *Dungeon Raid* blacksmith sells a **Sword** (10 damage) and an **Axe** (14),
and can enchant them: **Fire** adds +5 damage, **Sharp** adds +3. A weapon can
carry several enchantments.

## Smell

Each combination is its own class with hand-copied numbers: `FireSword`,
`SharpAxe`, `FireSharpSword`, ...

- 2 weapons and 2 enchantments already need 8 classes, and a third enchantment
  makes it 16
- the bonus lives in every class, not in one place
- `Forge` needs a branch per combination

## Consequence in the demo

- `FireSharpSword` was copied from `FireSword`, so it deals **15** instead of 18.
- `FireSharpAxe` does not exist. The order silently produces a plain `Fire Axe`
  with **19** damage instead of 22.

## Your task (live)

Add behavior by **wrapping** an object instead of subclassing:

```java
public abstract class EnchantedWeapon implements Weapon {
    protected final Weapon inner;
}

public class FireEnchant extends EnchantedWeapon {
    public String name() { return "Fire " + inner.name(); }
    public int damage()  { return inner.damage() + 5; }
}

Weapon weapon = new FireEnchant(new SharpEnchant(new Axe()));   // 22 damage
```

Delete all the combination classes. `Forge` only wraps the base weapon once per
enchantment.

## Hints

- A decorator has the **same interface** as the object it wraps, so a decorated
  weapon can be decorated again.
- Each decorator **delegates to `inner`**, then adds its part.
- A new enchantment is **one class**, and it works with every weapon.
- Wrapping order can matter: a "double damage" enchantment gives different
  results inside or outside `FireEnchant`.
- You know this pattern already: `new BufferedReader(new FileReader(file))`,
  `Collections.unmodifiableList(list)`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/decorator
```
