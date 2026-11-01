package gof.behavioral.observer;

/**
 * Observer smell: an enemy knows every system that cares about its death.
 *
 * <p>The monster has to import the HUD, the quest log and the audio engine, and a
 * new system (achievements, statistics, loot) means editing this class and all
 * its subclasses.
 */
public class Enemy {

    protected final String name;
    protected final Hud hud;
    protected final QuestLog quests;
    protected final AudioEngine audio;

    public Enemy(String name, Hud hud, QuestLog quests, AudioEngine audio) {
        this.name = name;
        this.hud = hud;
        this.quests = quests;
        this.audio = audio;
    }

    public void die() {
        hud.addKill();
        quests.enemyKilled(name);
        audio.play("death.wav");
    }
}

/** Plays a fanfare instead. Its {@code die()} was copied before quests existed. */
class Boss extends Enemy {

    Boss(String name, Hud hud, QuestLog quests, AudioEngine audio) {
        super(name, hud, quests, audio);
    }

    @Override
    public void die() {
        hud.addKill();
        audio.play("fanfare.wav");
    }
}
