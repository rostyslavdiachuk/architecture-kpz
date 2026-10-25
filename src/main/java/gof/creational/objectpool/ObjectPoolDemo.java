package gof.creational.objectpool;

import common.Console;

public class ObjectPoolDemo {

    public static void main(String[] args) {
        Console.header("GoF / Object Pool -- a new bolt for every shot");

        GameHeap heap = new GameHeap();
        Turret turret = new Turret(heap);
        GameLoop loop = new GameLoop(turret);

        Console.step("Boss fight: the crossbow turret fires 4 bolts/frame for 300 frames (5 s at 60 FPS)");
        loop.run(300);

        Console.note("bolts allocated: " + heap.allocations());
        Console.note("GC pauses:       " + heap.gcPauses() + " x " + GameHeap.GC_PAUSE_MS + " ms");
        Console.note("dropped frames:  " + loop.droppedFrames());
        Console.fail("at most ~60 bolts are ever in the air, yet " + heap.allocations()
                + " were allocated -- the GC froze the boss fight " + heap.gcPauses()
                + " times and the player sees stutter");
        Console.note("Refactor task: allocate the bolts once into a BoltPool; fire() acquires one "
                + "and resets it, a bolt that hits the wall is released back to the pool.");
    }
}
