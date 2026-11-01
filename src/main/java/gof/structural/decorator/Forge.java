package gof.structural.decorator;

/**
 * The blacksmith: picks the class that matches the player's order.
 *
 * <p>A third enchantment would double the number of classes, and double the
 * number of branches here too.
 */
public class Forge {

    public Weapon forge(String base, boolean fire, boolean sharp) {
        if (base.equals("Sword")) {
            if (fire && sharp) {
                return new FireSharpSword();
            }
            if (fire) {
                return new FireSword();
            }
            return sharp ? new SharpSword() : new Sword();
        }
        if (fire) {
            return new FireAxe();
        }
        return sharp ? new SharpAxe() : new Axe();
    }
}
