package gof.behavioral.memento;

/** The caretaker: decides when to save and reload, and never looks inside the memento. */
public class Campfire {

    private Hero.Memento lastSave;

    public void rest(Hero hero) {
        lastSave = hero.save();
    }

    public void reload(Hero hero) {
        hero.restore(lastSave);
    }
}
