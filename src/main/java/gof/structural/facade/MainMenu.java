package gof.structural.facade;

public class MainMenu {

    private final GameSession session;

    public MainMenu(GameSession session) {
        this.session = session;
    }

    public void onNewGame() {
        session.startLevel("Crypt");
    }
}
