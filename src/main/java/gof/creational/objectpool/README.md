# GoF / Creational / Object Pool

## Scenario

In the *Dungeon Raid* boss fight the crossbow turret fires **1 bolt per frame**
for **600 frames** (10 seconds at 60 FPS). Each bolt flies for 10 frames, hits
the wall and disappears, so **at most 10 bolts** are ever in the air at once.

Creating a bolt is expensive: its constructor loads the sprite and the
fire-trail effect (~5 ms).

## Smell

`Turret.fire()` does `new Bolt(...)` for **every shot**, and `update()` throws
away every bolt that hits the wall. The game keeps building the same expensive
object, uses it for a moment, and drops it.

## Consequence in the demo

- **600** bolts are created, although **10** would be enough.
- That is **3000 ms** spent loading the same sprite again and again.

## Your task (live)

Reuse the bolts instead of throwing them away:

1. Add `BoltPool` with a fixed capacity (10). Its constructor creates all bolts
   **once**, while the level loads.
   - `Bolt acquire()`: takes a free bolt, or returns `null` if none are left
   - `void release(Bolt bolt)`: puts the bolt back
2. `Bolt` gets a no-arg constructor and `reset(int x, int speed)`, which sets
   **every** field again.
3. `Turret.fire()` → `pool.acquire()` + `reset(...)`. `update()` →
   `pool.release(bolt)` when the bolt hits the wall.

## Hints

- Think of a **bowling alley's shoe rental**: shoes are not made per customer,
  they are handed out, returned, cleaned, and handed out again.
- **Always reset on reuse.** A bolt from the pool still has its old position.
  Forget to reset it and the "new" bolt starts at the wall.
- **Decide what happens when the pool is empty.** You can skip the shot (this
  solution does), create an extra bolt, or reuse the oldest one.
- **Never touch a bolt after releasing it.** Someone else may already own it.
- A pool is worth it only for objects that are **expensive to create** and
  **needed again and again** (bullets, particles, DB connections, threads).

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/objectpool
```
