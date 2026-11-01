package gof.behavioral.observer;

/** Observer: anything that wants to know when an enemy dies. */
public interface DeathListener {

    void onEnemyDied(Enemy enemy);
}
