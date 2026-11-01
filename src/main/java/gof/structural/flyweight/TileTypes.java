package gof.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/** The flyweight factory: creates each kind once, then hands out the same instance. */
public class TileTypes {

    private final Map<String, TileType> types = new HashMap<>();
    private final TextureLoader loader;

    public TileTypes(TextureLoader loader) {
        this.loader = loader;
    }

    public TileType get(String kind) {
        return types.computeIfAbsent(kind,
                k -> new TileType(k, loader.load(k + ".png"), !k.equals("wall")));
    }
}
