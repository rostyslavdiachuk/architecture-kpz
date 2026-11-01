package gof.behavioral.visitor;

/** How many points each object is worth. */
public class ScoreVisitor implements MapObjectVisitor<Integer> {

    @Override
    public Integer visitMonster(Monster monster) {
        return monster.xp();
    }

    @Override
    public Integer visitChest(Chest chest) {
        return chest.gold();
    }

    @Override
    public Integer visitTrap(Trap trap) {
        return 0;
    }

    @Override
    public Integer visitShrine(Shrine shrine) {
        return shrine.blessing();
    }
}
