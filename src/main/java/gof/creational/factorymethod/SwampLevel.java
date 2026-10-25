package gof.creational.factorymethod;

public class SwampLevel extends DungeonLevel {

    @Override
    public String biome() {
        return "Swamp";
    }

    @Override
    protected Enemy createEnemy() {
        return new SwampTroll();
    }
}
