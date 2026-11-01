# GoF / Behavioral / State

## Scenario

A *Dungeon Raid* hero can stand, jump, duck or be stunned. The same input means
different things in each state: `ATTACK` is a slash when standing, a dive attack in
the air and a low sweep when ducking. A **stunned** hero ignores all buttons until
they wake up.

## Smell

`HeroController.handle()` is one `switch` on the input, plus three booleans:

- three booleans allow 8 combinations, but only 4 are real states, and nothing
  rules the others out
- a new state means reviewing **every** branch
- the rules of one state are spread over the whole method

`stunned` was added last, and checked only in `JUMP`.

## Consequence in the demo

- A stunned hero can still **attack** and **duck**.
- Since that `DUCK` set a flag, after waking up the hero's `ATTACK` is a
  **low sweep** rather than a slash, though the player never asked to duck.

## Your task (live)

Give each state a class:

```java
public interface HeroState {
    String handle(HeroController hero, Input input);
}

class StunnedState implements HeroState {
    public String handle(HeroController hero, Input input) {
        if (input == Input.WAKE_UP) {
            hero.changeState(new StandingState());
            return "wake up";
        }
        return "ignored";
    }
}
```

Add `StandingState`, `JumpingState` and `DuckingState`. `HeroController` keeps one
`HeroState` field and delegates to it. Delete the booleans.

Draw the graph first: what are the states, and which input leads where?

## Hints

- All the rules of "stunned" are now **in one small class**.
- Impossible combinations are gone: there is one current state, so the hero cannot
  be jumping and ducking at once.
- Stateless states can be shared. One instance of each is enough.
- State vs Strategy: the code looks the same. With Strategy, the **client** picks
  the algorithm. With State, **the states switch themselves**, and the client doesn't
  usually notice.
- For a few simple states, an `enum` with a `switch` can be enough. Use classes when
  the states grow behavior of their own.

## Solution

```bash
git diff bad-practice-principles best-practice-principles -- src/main/java/gof/behavioral/state
```
