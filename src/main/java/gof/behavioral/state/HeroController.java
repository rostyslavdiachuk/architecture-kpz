package gof.behavioral.state;

/**
 * State smell: the hero's state is a pile of booleans, and each branch has to
 * check the right combination of them.
 *
 * <p>Three booleans allow eight combinations, but only four of them are real
 * states. {@code stunned} was added last and was checked only where the bug
 * report said so.
 */
public class HeroController {

    private boolean jumping;
    private boolean ducking;
    private boolean stunned;

    /** @return what the hero does in response */
    public String handle(Input input) {
        switch (input) {
            case JUMP:
                if (!jumping && !ducking && !stunned) {
                    jumping = true;
                    return "jump";
                }
                return "ignored";
            case LAND:
                jumping = false;
                return "land";
            case DUCK:
                if (!jumping) {
                    ducking = true;
                    return "duck";
                }
                return "ignored";
            case STAND_UP:
                ducking = false;
                return "stand up";
            case ATTACK:
                if (jumping) {
                    return "dive attack";
                }
                return ducking ? "low sweep" : "slash";
            case STUN:
                stunned = true;
                return "stunned";
            case WAKE_UP:
                stunned = false;
                return "wake up";
            default:
                return "ignored";
        }
    }
}
