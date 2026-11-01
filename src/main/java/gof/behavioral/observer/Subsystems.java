package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// The parts of the game that care when an enemy dies.

class Hud {
    private int kills;

    void addKill() {
        kills++;
    }

    int kills() {
        return kills;
    }
}

class QuestLog {
    private final List<String> killed = new ArrayList<>();

    void enemyKilled(String name) {
        killed.add(name);
    }

    boolean questDone(String target) {
        return killed.contains(target);
    }
}

class AudioEngine {
    private String lastSound;

    void play(String sound) {
        lastSound = sound;
    }

    String lastSound() {
        return lastSound;
    }
}
