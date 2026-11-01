package gof.behavioral.state;

/**
 * State: one class per state of the hero, holding all the rules of that state.
 *
 * <p>A state handles the input, chooses the next state and returns what the hero does.
 */
public interface HeroState {

    String handle(HeroController hero, Input input);
}

final class StandingState implements HeroState {
    @Override
    public String handle(HeroController hero, Input input) {
        switch (input) {
            case JUMP:
                hero.changeState(new JumpingState());
                return "jump";
            case DUCK:
                hero.changeState(new DuckingState());
                return "duck";
            case ATTACK:
                return "slash";
            case STUN:
                hero.changeState(new StunnedState());
                return "stunned";
            default:
                return "ignored";
        }
    }
}

final class JumpingState implements HeroState {
    @Override
    public String handle(HeroController hero, Input input) {
        switch (input) {
            case LAND:
                hero.changeState(new StandingState());
                return "land";
            case ATTACK:
                return "dive attack";
            case STUN:
                hero.changeState(new StunnedState());
                return "stunned";
            default:
                return "ignored";
        }
    }
}

final class DuckingState implements HeroState {
    @Override
    public String handle(HeroController hero, Input input) {
        switch (input) {
            case STAND_UP:
                hero.changeState(new StandingState());
                return "stand up";
            case ATTACK:
                return "low sweep";
            case STUN:
                hero.changeState(new StunnedState());
                return "stunned";
            default:
                return "ignored";
        }
    }
}

final class StunnedState implements HeroState {
    @Override
    public String handle(HeroController hero, Input input) {
        if (input == Input.WAKE_UP) {
            hero.changeState(new StandingState());
            return "wake up";
        }
        return "ignored";
    }
}
