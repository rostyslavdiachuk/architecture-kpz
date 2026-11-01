package gof.behavioral.chainofresponsibility;

/**
 * Chain of Responsibility: one step of the damage pipeline.
 *
 * <p>Each handler does its part, then decides whether to pass the rest of the hit on.
 */
public abstract class DamageHandler {

    private DamageHandler next;

    /** @return {@code next}, so that links can be chained */
    public DamageHandler linkWith(DamageHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Hero hero, int damage);

    protected void passOn(Hero hero, int damage) {
        if (next != null) {
            next.handle(hero, damage);
        }
    }
}

class InvulnerabilityHandler extends DamageHandler {
    @Override
    public void handle(Hero hero, int damage) {
        if (!hero.invulnerable()) {
            passOn(hero, damage);
        }
    }
}

class ShieldHandler extends DamageHandler {
    @Override
    public void handle(Hero hero, int damage) {
        int absorbed = Math.min(hero.shield(), damage);
        hero.setShield(hero.shield() - absorbed);
        passOn(hero, damage - absorbed);
    }
}

class ArmorHandler extends DamageHandler {
    @Override
    public void handle(Hero hero, int damage) {
        passOn(hero, Math.max(0, damage - hero.armor()));
    }
}

class HealthHandler extends DamageHandler {
    @Override
    public void handle(Hero hero, int damage) {
        hero.setHp(hero.hp() - damage);
    }
}
