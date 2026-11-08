namespace DesignPatterns.Gof.Structural.Flyweight;

/// <summary>Pixel data of one floor or wall image. Immutable.</summary>
public sealed record Texture(string File)
{
    public const int SizeKb = 64;
}
