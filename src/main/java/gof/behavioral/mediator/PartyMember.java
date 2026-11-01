package gof.behavioral.mediator;

/** One hero in a raid party. */
public abstract class PartyMember {

    private final String name;
    private boolean inTheFire = true;

    protected PartyMember(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void moveOutOfTheFire() {
        inTheFire = false;
    }

    public boolean inTheFire() {
        return inTheFire;
    }
}
