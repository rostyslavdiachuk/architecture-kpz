package gof.structural.facade;

// The engine's subsystems. They work fine, but only when called in the right order.

class AssetLoader {
    private boolean loaded;

    void load(String level) {
        loaded = true;
    }

    boolean loaded() {
        return loaded;
    }
}

class WorldGenerator {
    private int rooms;

    /** Needs the assets to be loaded, otherwise there is nothing to build rooms from. */
    void generate(AssetLoader assets) {
        rooms = assets.loaded() ? 12 : 0;
    }

    int rooms() {
        return rooms;
    }
}

class EnemySpawner {
    private int enemies;

    /** Needs the world to exist: two enemies per room. */
    void populate(WorldGenerator world) {
        enemies = world.rooms() * 2;
    }

    int enemies() {
        return enemies;
    }
}

class AudioEngine {
    private String theme;

    void playTheme(String level) {
        theme = level;
    }

    String theme() {
        return theme;
    }
}

class Hud {
    private boolean visible;

    void show() {
        visible = true;
    }

    boolean visible() {
        return visible;
    }
}
