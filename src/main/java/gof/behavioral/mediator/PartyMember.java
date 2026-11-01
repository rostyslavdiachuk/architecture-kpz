package gof.behavioral.mediator;

/** One hero in a raid party. It knows the raid, and none of the other members. */
public abstract class PartyMember {

    private final String name;
    private final Raid raid;
    private boolean inTheFire = true;

    protected PartyMember(String name, Raid raid) {
        this.name = name;
        this.raid = raid;
        raid.join(this);
    }

    public String name() {
        return name;
    }

    public void warnParty() {
        raid.warnParty(this);
    }

    public void moveOutOfTheFire() {
        inTheFire = false;
    }

    public boolean inTheFire() {
        return inTheFire;
    }
}
