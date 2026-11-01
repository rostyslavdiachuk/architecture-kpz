package gof.structural.facade;

public class ContinueButton {

    private final GameSession session;

    public ContinueButton(GameSession session) {
        this.session = session;
    }

    public void onClick() {
        session.startLevel("Crypt");
    }
}
