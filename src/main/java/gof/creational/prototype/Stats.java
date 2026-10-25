package gof.creational.prototype;

/** Combat numbers of one enemy. Mutable: they change while the enemy fights. */
public class Stats {

    private int hp;
    private int attack;
    private int iceResistance;

    public Stats(int hp, int attack, int iceResistance) {
        this.hp = hp;
        this.attack = attack;
        this.iceResistance = iceResistance;
    }

    /** Copy constructor: every clone needs its own HP bar. */
    public Stats(Stats other) {
        this(other.hp, other.attack, other.iceResistance);
    }

    public int hp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int attack() {
        return attack;
    }

    public int iceResistance() {
        return iceResistance;
    }

    public void setIceResistance(int iceResistance) {
        this.iceResistance = iceResistance;
    }

    @Override
    public String toString() {
        return "hp=" + hp + ", atk=" + attack + ", iceRes=" + iceResistance + "%";
    }
}
