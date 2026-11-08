# GoF patterns in C#

The C# edition of the *Dungeon Raid* GoF examples. There are two branches:

| Branch | Contents |
|---|---|
| `bad-practice-principles-csharp` | code with the smell, and a README with the task |
| `best-practice-principles-csharp` | the refactored solution, plus C#-specific variants |

Patterns so far: **Flyweight**, **Composite**, **Bridge** (`src/DesignPatterns/Gof/Structural`).

## Run

Requires the .NET 9 SDK.

```bash
dotnet run --project src/DesignPatterns -- flyweight   # or composite, bridge
dotnet run --project src/DesignPatterns                # every demo
```

A .NET project has a single entry point, so `Program.cs` picks the demo by name
(the Java version has one `main()` per demo).

## Solution for one pattern

```bash
git diff bad-practice-principles-csharp best-practice-principles-csharp -- src/DesignPatterns/Gof/Structural/Bridge
```
