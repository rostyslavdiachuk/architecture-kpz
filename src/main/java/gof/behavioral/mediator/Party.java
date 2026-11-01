package gof.behavioral.mediator;

// The roles. No role refers to another one.

class Tank extends PartyMember {
    Tank(Raid raid) {
        super("Tank", raid);
    }
}

class Healer extends PartyMember {
    Healer(Raid raid) {
        super("Healer", raid);
    }
}

class Mage extends PartyMember {
    Mage(Raid raid) {
        super("Mage", raid);
    }
}

class Rogue extends PartyMember {
    Rogue(Raid raid) {
        super("Rogue", raid);
    }
}
