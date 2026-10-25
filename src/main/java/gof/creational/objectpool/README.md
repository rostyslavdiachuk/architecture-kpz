# GoF / Creational / Object Pool

## Scenario

In the *Dungeon Raid* boss fight the crossbow turret shoots in **volleys**: it
fires **5 bolts** at a monster, they hit, and then it fires the next volley.
The fight lasts **10 volleys**.

Creating a bolt is expensive: its constructor loads the sprite and the
fire-trail effect (~5 ms).

## Smell

`Turret.fireVolley()` does `new Bolt(...)` for **every bolt of every volley**
and throws the bolts away after they hit. The game keeps building the same
expensive object, uses it for a moment, and drops it.

## Consequence in the demo

The demo prints "bolts created so far" after each volley: 5, 10, 15 … **50**.

- **50** bolts are created, although **5** would be enough.
- That is **250 ms** spent loading the same sprite again and again.

## Your task (live)

Reuse the bolts instead of throwing them away:

1. Add `BoltPool` with a fixed capacity (5). Its constructor creates all bolts
   **once**, while the level loads.
   - `Bolt acquire()`: takes a free bolt, or returns `null` if none are left
   - `void release(Bolt bolt)`: puts the bolt back
2. `Bolt` gets a no-arg constructor and `reset(String target)`, which sets
   **every** field again.
3. `Turret.fireVolley()` → `pool.acquire()` + `reset(target)` for each bolt,
   then `pool.release(bolt)` after the bolts hit.

After the refactor, "bolts created so far" stays at **5** for every volley.

## Hints

- Think of a **bowling alley's shoe rental**: shoes are not made per customer,
  they are handed out, returned, cleaned, and handed out again.
- **Always reset on reuse.** A bolt from the pool still remembers its old
  target. Forget to reset it and the volley at the Troll hits the Goblin.
- **Decide what happens when the pool is empty.** You can skip the shot (this
  solution does), create an extra bolt, or reuse the oldest one.
- **Never touch a bolt after releasing it.** Someone else may already own it.
- A pool is worth it only for objects that are **expensive to create** and
  **needed again and again** (bullets, particles, DB connections, threads).

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/objectpool
```
