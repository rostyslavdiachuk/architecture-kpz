package gof.behavioral.mediator;

// Mediator smell: every party member holds a direct reference to the others.
// Four members already mean 9 links, and 12 once the others learn about the
// rogue. Whoever warns the party has to name everyone, and each class has its
// own copy of that list.

class Tank extends PartyMember {
    private Healer healer;
    private Mage mage;

    Tank() {
        super("Tank");
    }

    void meet(Healer healer, Mage mage) {
        this.healer = healer;
        this.mage = mage;
    }

    /** The tank sees the boss winding up its fire breath. Written before the rogue joined. */
    void warnParty() {
        moveOutOfTheFire();
        healer.moveOutOfTheFire();
        mage.moveOutOfTheFire();
    }
}

class Healer extends PartyMember {
    private Tank tank;
    private Mage mage;

    Healer() {
        super("Healer");
    }

    void meet(Tank tank, Mage mage) {
        this.tank = tank;
        this.mage = mage;
    }

    void warnParty() {
        moveOutOfTheFire();
        tank.moveOutOfTheFire();
        mage.moveOutOfTheFire();
    }
}

class Mage extends PartyMember {
    private Tank tank;
    private Healer healer;

    Mage() {
        super("Mage");
    }

    void meet(Tank tank, Healer healer) {
        this.tank = tank;
        this.healer = healer;
    }

    void warnParty() {
        moveOutOfTheFire();
        tank.moveOutOfTheFire();
        healer.moveOutOfTheFire();
    }
}

/** Joined the game in the last patch. It knows everyone, but nobody knows it. */
class Rogue extends PartyMember {
    private Tank tank;
    private Healer healer;
    private Mage mage;

    Rogue() {
        super("Rogue");
    }

    void meet(Tank tank, Healer healer, Mage mage) {
        this.tank = tank;
        this.healer = healer;
        this.mage = mage;
    }

    void warnParty() {
        moveOutOfTheFire();
        tank.moveOutOfTheFire();
        healer.moveOutOfTheFire();
        mage.moveOutOfTheFire();
    }
}
