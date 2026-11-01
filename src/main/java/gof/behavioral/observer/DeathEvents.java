package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/** The subject: enemies publish here, without knowing who is listening. */
public class DeathEvents {

    private final List<DeathListener> listeners = new ArrayList<>();

    public void subscribe(DeathListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(DeathListener listener) {
        listeners.remove(listener);
    }

    public void publish(Enemy enemy) {
        for (DeathListener listener : listeners) {
            listener.onEnemyDied(enemy);
        }
    }
}
