package gof.structural.flyweight;

/** The extrinsic state: only the position is unique to a tile, the rest is shared. */
public record Tile(int x, int y, TileType type) {

    public boolean walkable() {
        return type.walkable();
    }
}
