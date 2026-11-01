# GoF / Structural / Adapter

## Scenario

*Dungeon Raid* reads player input through `InputDevice`: a direction (-1, 0, +1)
and an attack button. The keyboard already works. Now the game has to support
the **XPad** gamepad, and its vendor ships a library we cannot change:
`XPadSdk` reports the stick as a raw number from -32768 to 32767 and the buttons
as a bit mask.

## Smell

`HeroController` holds both a `Keyboard` and an `XPadSdk` and branches in
`tick()`:

- the game logic now knows the vendor's API and its value ranges
- converting the stick's value is the mover's job, and it was forgotten
- each new gamepad means another field, constructor and `else`

## Consequence in the demo

- Keyboard: 3 ticks → the hero walks **3** tiles.
- XPad: 3 ticks → the hero is at tile **98301**.

## Your task (live)

Wrap the SDK in a class that speaks the game's interface:

```java
public class XPadAdapter implements InputDevice {
    private final XPadSdk xpad;

    public int moveX() { /* raw stick value -> -1, 0, +1 */ }
    public boolean attackPressed() { /* read the bit mask */ }
}
```

Then reduce `HeroController` to one field, `InputDevice device`, and delete the
`if/else`.

## Hints

- The adapter **translates**. It holds no game logic, only conversions.
- `XPadSdk` does not change, and neither does `Keyboard`.
- Real sticks never rest at exactly 0. Treat small values as 0 (a **dead zone**),
  or the hero slowly walks off by themselves. The adapter is the right place for it.
- A new gamepad is one new adapter. `HeroController` stays as it is.
- You know this pattern already: `Arrays.asList()`, `InputStreamReader`.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/structural/adapter
```
