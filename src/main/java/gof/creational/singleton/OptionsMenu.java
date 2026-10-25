package gof.creational.singleton;

import common.Console;

/** The pause-screen menu where the player tweaks the game. */
public class OptionsMenu {

    private final GameSettings settings = GameSettings.getInstance();

    public void chooseDifficulty(Difficulty difficulty) {
        settings.setDifficulty(difficulty);
        Console.step("Options: difficulty -> " + difficulty);
    }

    public void muteMusic() {
        settings.setMusicVolume(0);
        Console.step("Options: music volume -> 0 (muted)");
    }
}
