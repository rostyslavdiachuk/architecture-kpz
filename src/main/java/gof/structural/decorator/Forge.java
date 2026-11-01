package gof.structural.decorator;

/** The blacksmith: takes a base weapon and wraps it once per enchantment. */
public class Forge {

    public Weapon forge(String base, boolean fire, boolean sharp) {
        Weapon weapon = base.equals("Sword") ? new Sword() : new Axe();
        if (sharp) {
            weapon = new SharpEnchant(weapon);
        }
        if (fire) {
            weapon = new FireEnchant(weapon);
        }
        return weapon;
    }
}
