package gof.behavioral.chainofresponsibility;

/**
 * Chain of Responsibility smell: one method does every step of the damage
 * pipeline, in a fixed order.
 *
 * <p>The steps cannot be reordered, reused or tested on their own. The
 * invulnerability potion was added at the bottom, next to the HP code, so
 * by then the earlier steps have already done their work.
 */
public class DamageResolver {

    public void resolve(Hero hero, int damage) {
        int absorbed = Math.min(hero.shield(), damage);
        hero.setShield(hero.shield() - absorbed);
        int rest = damage - absorbed;

        rest = Math.max(0, rest - hero.armor());

        if (hero.invulnerable()) {
            return;
        }
        hero.setHp(hero.hp() - rest);
    }
}
