package gof.behavioral.visitor;

/** Which symbol each object has on the minimap. */
public class MinimapVisitor implements MapObjectVisitor<String> {

    @Override
    public String visitMonster(Monster monster) {
        return "M";
    }

    @Override
    public String visitChest(Chest chest) {
        return "$";
    }

    @Override
    public String visitTrap(Trap trap) {
        return "^";
    }

    @Override
    public String visitShrine(Shrine shrine) {
        return "S";
    }
}
