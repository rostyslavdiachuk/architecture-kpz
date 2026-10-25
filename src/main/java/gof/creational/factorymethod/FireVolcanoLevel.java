package gof.creational.factorymethod;

public class FireVolcanoLevel extends DungeonLevel {

    @Override
    public String biome() {
        return "Fire";
    }

    @Override
    protected Enemy createEnemy() {
        return new FireImp();
    }
}
