namespace DesignPatterns.Common;

/// <summary>
/// Tiny logging helper so the demo output reads well on a projector.
/// </summary>
/// <remarks>
/// This class is intentionally trivial and is <b>not</b> part of any lesson.
/// It only prefixes lines so the narrative of each demo is easy to follow:
/// <code>
///   ===    section header
///   &gt;&gt;     a step the system performs
///   [OK]   the system behaved correctly
///   [WARN] something suspicious but not fatal
///   [FAIL] the system misbehaved (wrong result, dropped work, crash)
///   ..     a plain note
/// </code>
/// Named <c>Out</c> rather than <c>Console</c> so it does not clash with <see cref="System.Console"/>.
/// </remarks>
public static class Out
{
    public static void Header(string title)
    {
        Console.WriteLine();
        Console.WriteLine($"=== {title} ===");
    }

    public static void Step(string message) => Console.WriteLine($">>  {message}");

    public static void Ok(string message) => Console.WriteLine($"[OK] {message}");

    public static void Warn(string message) => Console.WriteLine($"[WARN] {message}");

    public static void Fail(string message) => Console.WriteLine($"[FAIL] {message}");

    public static void Note(string message) => Console.WriteLine($"..  {message}");
}
