namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>Pretends to decode an image into memory. Each call takes another 64 KB.</summary>
public class TextureLoader
{
    public int Loads { get; private set; }

    public long MemoryKb => Loads * (long)Texture.SizeKb;

    public Texture Load(string file)
    {
        Loads++;
        return new Texture(file);
    }
}
