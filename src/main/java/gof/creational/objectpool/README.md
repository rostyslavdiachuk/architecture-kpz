# GoF / Creational / Object Pool

## Scenario

In the *Dungeon Raid* boss fight the crossbow turret fires **4 bolts per frame**
for **300 frames** (5 seconds at 60 FPS). Each bolt flies for 15 frames, hits the
wall and disappears, so at most ~60 bolts are ever in the air at once. The game
must hold 60 FPS: every frame has a 16 ms budget.

## Smell

`Turret.fire()` does `new Bolt(...)` for **every shot**, and `update()` just drops
bolts that hit the wall. Objects that live a quarter of a second are created and
thrown away in a tight loop. `GameHeap` simulates what the JVM does about that:
allocations fill the young generation, and when it is full the GC freezes the
game for 40 ms.

## Consequence in the demo

- **1200** bolts are allocated, although **~60** would be enough.
- The GC freezes the game **9 times**, which means **9 dropped frames**. The
  player sees the boss fight stutter.

## Your task (live)

Reuse the bolts instead of throwing them away:

1. Add `BoltPool` with a fixed capacity (64). Its constructor allocates all bolts
   **once**, while the level loads.
   - `Bolt acquire()`: takes a free bolt, or returns `null` if none are left
   - `void release(Bolt bolt)`: gives the bolt back
2. `Bolt` gets a no-arg constructor and `reset(int x, int speed)`, which
   re-initializes **every** field.
3. `Turret.fire()` → `pool.acquire()` + `reset(...)`. `update()` →
   `pool.release(bolt)` when the bolt hits the wall.

## Hints

- **Always reset on reuse.** A bolt from the pool still holds the old position
  and speed. If you forget one field, a "new" bolt appears in the middle of the
  screen or flies the wrong way.
- **Decide what happens when the pool is empty.** You can skip the shot (this
  solution does, and counts it), grow the pool, or recycle the oldest bolt.
- **Never touch a bolt after releasing it.** Someone else may already own it.
- A pool pays off only when objects are expensive to create or short-lived in
  huge numbers (bullets, particles, DB connections, threads). For ordinary
  objects, the JVM allocator is faster than any pool you write.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/creational/objectpool
```
