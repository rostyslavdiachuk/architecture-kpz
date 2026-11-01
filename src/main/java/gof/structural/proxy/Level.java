package gof.structural.proxy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Proxy smell: every texture is loaded up front, needed or not.
 *
 * <p>The level holds real {@link HdTexture}s, so creating the object and paying
 * for it are the same step. Most rooms are never seen, but they are all paid for
 * before the first frame.
 */
public class Level {

    private final Map<String, HdTexture> textures = new LinkedHashMap<>();

    public Level(int rooms) {
        for (int i = 1; i <= rooms; i++) {
            textures.put("Room " + i, new HdTexture("room" + i + ".dds"));
        }
    }

    public String enter(String room) {
        return textures.get(room).draw();
    }
}
