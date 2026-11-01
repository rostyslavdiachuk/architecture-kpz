package gof.structural.flyweight;

/** Pretends to decode an image into memory. Each call takes another 64 KB. */
public class TextureLoader {

    private int loads;

    public Texture load(String file) {
        loads++;
        return new Texture(file);
    }

    public int loads() {
        return loads;
    }

    public long memoryKb() {
        return loads * (long) Texture.SIZE_KB;
    }
}
