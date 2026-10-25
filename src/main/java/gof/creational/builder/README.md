# GoF / Creational / Builder

## Scenario

The *Dungeon Raid* character creator makes heroes. A hero has a name and a class
(required), plus HP, mana, strength, weapon, armor and a pet. Most of those are
optional or have sensible defaults.

## Smell

`Hero` has one constructor with **eight positional parameters**:

```java
new Hero("Aria", HeroClass.MAGE, 150, 80, 5, "Fire Staff", null, "Owl");
```

- three `int`s and three `String`s in a row: swap two and it still compiles
- optional parts are passed as `null`
- the reader cannot tell which number is HP and which is mana
- there is no step where the hero can be **validated** before it exists

## Consequence in the demo

- Aria should have 80 HP and 150 mana, but got **150 HP and 80 mana**.
- Borin's constructor call is unreadable without opening `Hero.java`.
- **Nox, a mage with 0 mana**, is created without any complaint.

## Your task (live)

Add a builder and make the constructor private:

```java
Hero aria = Hero.builder("Aria", HeroClass.MAGE)   // required parts
        .hp(80)
        .mana(150)
        .weapon("Fire Staff")
        .pet("Owl")
        .build();                                  // validates, then creates
```

- `Hero.Builder` holds the values, with defaults (`armor = "Cloth"`, `pet = "none"`, ...).
- `build()` checks the rules, e.g. "a mage needs mana > 0", and throws if they
  are broken.
- `Hero` becomes immutable: all fields `final`, no setters.

## Hints

- Required parameters go into `builder(...)`. Optional ones get a named method.
- Each builder method returns `this`, so calls can be chained.
- Validation belongs in `build()`. Either you get a valid hero or you get an
  exception, never a broken hero.
- You know this pattern already: `StringBuilder`, `HttpRequest.newBuilder()`,
  `Stream.builder()`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/builder
```
