package gof.creational.prototype;

/** Pretends to read meshes from disk. Every load costs ~100 ms. */
public class AssetLoader {

    private static final int MS_PER_LOAD = 100;

    private int loads;

    public Mesh loadMesh(String file) {
        loads++;
        return new Mesh(file, 12_000);
    }

    public int loads() {
        return loads;
    }

    public int millisSpentLoading() {
        return loads * MS_PER_LOAD;
    }
}
