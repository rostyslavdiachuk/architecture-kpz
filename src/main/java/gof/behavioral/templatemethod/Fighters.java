package gof.behavioral.templatemethod;

// Template Method smell: the rules of a turn are the same for everyone, but each
// class has its own copy of them. Only the action in the middle really differs.

class Warrior extends Fighter {

    Warrior() {
        super("Warrior");
    }

    @Override
    public String takeTurn() {
        if (stunned) {
            stunned = false;
            return "skips the turn";
        }
        if (poisoned) {
            hp -= POISON_DAMAGE;
        }
        return "swings the axe";
    }
}

class Mage extends Fighter {

    Mage() {
        super("Mage");
    }

    @Override
    public String takeTurn() {
        if (stunned) {
            stunned = false;
            return "skips the turn";
        }
        // copied from Warrior before poison was added to the game
        mana += 10;
        return "casts a fireball";
    }
}

class Rogue extends Fighter {

    Rogue() {
        super("Rogue");
    }

    @Override
    public String takeTurn() {
        // copied from Warrior, and the stun check got lost in a merge
        if (poisoned) {
            hp -= POISON_DAMAGE;
        }
        return "stabs in the back";
    }
}
