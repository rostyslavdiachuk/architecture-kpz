package gof.structural.flyweight;

/** Pixel data of one floor or wall image. Immutable. */
public record Texture(String file) {

    public static final int SIZE_KB = 64;
}
