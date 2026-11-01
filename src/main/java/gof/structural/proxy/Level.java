package gof.structural.proxy;

import java.util.LinkedHashMap;
import java.util.Map;

/** Holds a {@link Texture} per room. It cannot tell that they are proxies. */
public class Level {

    private final Map<String, Texture> textures = new LinkedHashMap<>();

    public Level(int rooms) {
        for (int i = 1; i <= rooms; i++) {
            textures.put("Room " + i, new TextureProxy("room" + i + ".dds"));
        }
    }

    public String enter(String room) {
        return textures.get(room).draw();
    }
}
