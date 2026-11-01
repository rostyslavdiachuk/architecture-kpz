package gof.structural.proxy;

/**
 * Virtual proxy: stands in for an {@link HdTexture} and has the same interface.
 *
 * <p>Creating a proxy costs nothing. The real texture is loaded on the first
 * {@link #draw()} and reused after that.
 */
public class TextureProxy implements Texture {

    private final String file;
    private HdTexture real;

    public TextureProxy(String file) {
        this.file = file;
    }

    @Override
    public String draw() {
        if (real == null) {
            real = new HdTexture(file);
        }
        return real.draw();
    }
}
