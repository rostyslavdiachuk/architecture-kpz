package gof.behavioral.command;

/** Command: an action as an object, which can be stored, undone or replayed. */
public interface Command {

    void execute();

    void undo();
}

final class MoveCommand implements Command {

    private final Hero hero;
    private final int tiles;

    MoveCommand(Hero hero, int tiles) {
        this.hero = hero;
        this.tiles = tiles;
    }

    @Override
    public void execute() {
        hero.moveBy(tiles);
    }

    @Override
    public void undo() {
        hero.moveBy(-tiles);
    }
}

final class DrinkPotionCommand implements Command {

    private final Hero hero;
    private int hpBefore;

    DrinkPotionCommand(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void execute() {
        hpBefore = hero.hp();
        hero.drinkPotion();
    }

    @Override
    public void undo() {
        hero.takePotionBack(hpBefore);
    }
}
