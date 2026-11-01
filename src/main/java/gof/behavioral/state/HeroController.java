package gof.behavioral.state;

/** Holds the current state and delegates each input to it. No booleans left. */
public class HeroController {

    private HeroState state = new StandingState();

    /** @return what the hero does in response */
    public String handle(Input input) {
        return state.handle(this, input);
    }

    void changeState(HeroState state) {
        this.state = state;
    }
}
