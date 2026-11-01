package gof.structural.flyweight;

/**
 * Flyweight smell: each tile keeps its own copy of data that is the same for
 * every tile of its kind.
 *
 * <p>Only {@code x} and {@code y} are unique to a tile. The kind, the texture and
 * the walkable flag are identical for all floor tiles, yet each tile holds them.
 */
public class Tile {

    private final int x;
    private final int y;
    private final String kind;
    private final Texture texture;
    private final boolean walkable;

    public Tile(int x, int y, String kind, TextureLoader loader) {
        this.x = x;
        this.y = y;
        this.kind = kind;
        this.texture = loader.load(kind + ".png");
        this.walkable = !kind.equals("wall");
    }

    public boolean walkable() {
        return walkable;
    }

    public String kind() {
        return kind;
    }
}
