package gof.creational.singleton;

/** Plays the dungeon soundtrack at the volume from the settings. */
public class AudioEngine {

    private final GameSettings settings = new GameSettings();

    /** @return the volume the soundtrack is actually played at */
    public int playDungeonTheme() {
        return settings.musicVolume();
    }
}
