using DesignPatterns.Gof.Structural.Bridge;
using DesignPatterns.Gof.Structural.Composite;
using DesignPatterns.Gof.Structural.Flyweight;

// Java has one main() per demo; a .NET project has one entry point, so it dispatches by name:
//   dotnet run --project src/DesignPatterns -- flyweight
//   dotnet run --project src/DesignPatterns            (runs every demo)
var demos = new Dictionary<string, Action>(StringComparer.OrdinalIgnoreCase)
{
    ["flyweight"] = FlyweightDemo.Run,
    ["composite"] = CompositeDemo.Run,
    ["bridge"] = BridgeDemo.Run,
};

if (args.Length == 0)
{
    foreach (var demo in demos.Values)
    {
        demo();
    }
    return 0;
}

if (!demos.TryGetValue(args[0], out var selected))
{
    Console.Error.WriteLine($"Unknown demo '{args[0]}'. Available: {string.Join(", ", demos.Keys)}");
    return 1;
}

selected();
return 0;
