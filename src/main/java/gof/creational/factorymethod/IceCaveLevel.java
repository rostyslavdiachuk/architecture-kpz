package gof.creational.factorymethod;

public class IceCaveLevel extends DungeonLevel {

    @Override
    public String biome() {
        return "Ice";
    }

    @Override
    protected Enemy createEnemy() {
        return new IceGolem();
    }
}
