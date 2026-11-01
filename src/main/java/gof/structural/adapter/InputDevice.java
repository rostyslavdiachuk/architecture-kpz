package gof.structural.adapter;

/** What the game expects from a controller: direction -1 / 0 / +1 and one attack button. */
public interface InputDevice {

    int moveX();

    boolean attackPressed();
}

final class Keyboard implements InputDevice {

    private int direction;
    private boolean attack;

    void holdDirection(int direction) {
        this.direction = direction;
    }

    void holdAttack(boolean attack) {
        this.attack = attack;
    }

    public int moveX() {
        return direction;
    }

    public boolean attackPressed() {
        return attack;
    }
}
