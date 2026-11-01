package gof.structural.proxy;

/** A 4K room texture. The constructor reads it from disk, which takes ~400 ms. */
public class HdTexture {

    public static final int LOAD_COST_MS = 400;

    private static int loads;

    private final String file;

    public HdTexture(String file) {
        loads++; // pretend: read and decode the file
        this.file = file;
    }

    public static int loads() {
        return loads;
    }

    public String draw() {
        return "drawing " + file;
    }
}
