package gof.behavioral.templatemethod;

// Each fighter writes only what makes it different.

class Warrior extends Fighter {

    Warrior() {
        super("Warrior");
    }

    @Override
    protected String act() {
        return "swings the axe";
    }
}

class Mage extends Fighter {

    private int mana;

    Mage() {
        super("Mage");
    }

    @Override
    protected String act() {
        return "casts a fireball";
    }

    @Override
    protected void afterAct() {
        mana += 10;
    }
}

class Rogue extends Fighter {

    Rogue() {
        super("Rogue");
    }

    @Override
    protected String act() {
        return "stabs in the back";
    }
}
