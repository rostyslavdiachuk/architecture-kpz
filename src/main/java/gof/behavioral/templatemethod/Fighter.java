package gof.behavioral.templatemethod;

/** Someone in a turn-based fight. Subclasses write their own {@link #takeTurn()}. */
public abstract class Fighter {

    protected static final int POISON_DAMAGE = 5;

    private final String name;
    protected int hp = 100;
    protected int mana;
    protected boolean stunned;
    protected boolean poisoned;

    protected Fighter(String name) {
        this.name = name;
    }

    /** @return what the fighter did this turn */
    public abstract String takeTurn();

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
