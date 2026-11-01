package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// The parts of the game that care when an enemy dies. Each one subscribes by itself.

class Hud implements DeathListener {
    private int kills;

    @Override
    public void onEnemyDied(Enemy enemy) {
        kills++;
    }

    int kills() {
        return kills;
    }
}

class QuestLog implements DeathListener {
    private final List<String> killed = new ArrayList<>();

    @Override
    public void onEnemyDied(Enemy enemy) {
        killed.add(enemy.name());
    }

    boolean questDone(String target) {
        return killed.contains(target);
    }
}

class AudioEngine implements DeathListener {
    private String lastSound;

    @Override
    public void onEnemyDied(Enemy enemy) {
        lastSound = enemy.isBoss() ? "fanfare.wav" : "death.wav";
    }

    String lastSound() {
        return lastSound;
    }
}

/** The new feature. {@link Enemy} did not have to change for it. */
class Achievements implements DeathListener {
    private boolean bossSlayer;

    @Override
    public void onEnemyDied(Enemy enemy) {
        bossSlayer |= enemy.isBoss();
    }

    boolean bossSlayer() {
        return bossSlayer;
    }
}
