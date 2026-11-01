package gof.structural.flyweight;

/**
 * Flyweight: the intrinsic state, shared by all tiles of one kind.
 *
 * <p>Immutable, because it is shared: a change would affect every tile of the kind.
 */
public record TileType(String kind, Texture texture, boolean walkable) {
}
