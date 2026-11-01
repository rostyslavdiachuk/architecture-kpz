# GoF / Structural / Proxy

## Scenario

A *Dungeon Raid* level has **20 rooms**, each with a 4K texture that takes ~400 ms
to load. Most players see only a few rooms before they die.

## Smell

`Level` creates a real `HdTexture` for every room in its constructor. Having the
object and paying for it are the same step, so the cost comes up front whether
the room is ever seen or not.

## Consequence in the demo

The player visits 2 rooms, but **20** textures are loaded: **8000 ms** of loading
screen, and 18 of those textures are never drawn.

## Your task (live)

1. Extract an interface:
   ```java
   public interface Texture { String draw(); }
   ```
   `HdTexture` implements it and stays expensive. Don't change how it loads.
2. Add a stand-in with the same interface:
   ```java
   public class TextureProxy implements Texture {
       private final String file;
       private HdTexture real;     // null until someone really needs it

       public String draw() { /* create real on first use, then delegate */ }
   }
   ```
3. `Level` stores `Texture`s and creates proxies. Its `enter()` does not change.

After the refactor, the demo loads **2** textures, and Room 1 is not loaded twice.

## Hints

- The client cannot tell the difference: a proxy has the **same interface** as
  the real object.
- This is a **virtual proxy** (delay the cost). The same shape also gives a
  **protection proxy** (check rights before delegating), a **caching proxy**
  (remember results) and a **remote proxy** (the object is on another machine).
- Proxy vs Decorator: they look alike. A decorator **adds behavior**, and the
  client chooses to wrap. A proxy **controls access**, and usually manages the
  real object's lifetime.
- You know this pattern already: Hibernate's lazy entities, Spring's
  `@Transactional` beans.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/proxy
```
