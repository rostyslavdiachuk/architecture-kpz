package gof.creational.prototype;

/** A 3D model loaded from disk. Immutable, so it is safe to share between enemies. */
public record Mesh(String file, int triangles) {
}
