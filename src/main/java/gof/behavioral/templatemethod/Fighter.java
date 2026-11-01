package gof.behavioral.templatemethod;

/**
 * Template Method: {@link #takeTurn()} is the skeleton of a turn, and it is the same
 * for everyone.
 *
 * <p>It is {@code final}, so a subclass cannot change the rules. A subclass fills in
 * {@link #act()} and may override the {@link #afterAct()} hook.
 */
public abstract class Fighter {

    protected static final int POISON_DAMAGE = 5;

    private final String name;
    private int hp = 100;
    private boolean stunned;
    private boolean poisoned;

    protected Fighter(String name) {
        this.name = name;
    }

    /** @return what the fighter did this turn */
    public final String takeTurn() {
        if (stunned) {
            stunned = false;
            return "skips the turn";
        }
        if (poisoned) {
            hp -= POISON_DAMAGE;
        }
        String action = act();
        afterAct();
        return action;
    }

    /** The step that differs for every fighter. */
    protected abstract String act();

    /** A hook: does nothing unless a subclass needs it. */
    protected void afterAct() {
    }

    public String name() {
        return name;
    }

    public int hp() {
        return hp;
    }

    public void stun() {
        stunned = true;
    }

    public void poison() {
        poisoned = true;
    }
}
