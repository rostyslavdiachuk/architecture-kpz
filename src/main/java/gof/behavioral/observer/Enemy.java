package gof.behavioral.observer;

/** An enemy only announces its death. It does not know who cares. */
public class Enemy {

    private final String name;
    private final DeathEvents events;

    public Enemy(String name, DeathEvents events) {
        this.name = name;
        this.events = events;
    }

    public String name() {
        return name;
    }

    public boolean isBoss() {
        return false;
    }

    public void die() {
        events.publish(this);
    }
}

class Boss extends Enemy {

    Boss(String name, DeathEvents events) {
        super(name, events);
    }

    @Override
    public boolean isBoss() {
        return true;
    }
}
