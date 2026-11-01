package gof.structural.proxy;

import common.Console;

public class ProxyDemo {

    public static void main(String[] args) {
        Console.header("GoF / Proxy -- all 20 room textures are loaded before the level starts");

        Console.step("Loading a level with 20 rooms");
        Level level = new Level(20);
        Console.note("textures loaded before the first frame: " + HdTexture.loads());

        Console.step("The player visits Room 1, Room 2, then goes back to Room 1, and dies");
        for (String room : new String[]{"Room 1", "Room 2", "Room 1"}) {
            Console.note(room + ": " + level.enter(room));
        }

        int loads = HdTexture.loads();
        if (loads == 2) {
            Console.ok("only the 2 visited rooms were loaded (" + loads * HdTexture.LOAD_COST_MS + " ms)");
        } else {
            Console.fail(loads + " textures were loaded for 2 visited rooms -- "
                    + loads * HdTexture.LOAD_COST_MS + " ms of loading screen, mostly wasted");
        }

        Console.note("Refactor task: extract a Texture interface and add a TextureProxy that "
                + "creates the HdTexture on the first draw() and then reuses it.");
    }
}
