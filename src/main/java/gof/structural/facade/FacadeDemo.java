package gof.structural.facade;

import common.Console;

public class FacadeDemo {

    public static void main(String[] args) {
        Console.header("GoF / Facade -- every button drives the engine subsystems itself");

        Console.step("First launch: the player clicks 'New game'");
        AssetLoader assets = new AssetLoader();
        WorldGenerator world = new WorldGenerator();
        EnemySpawner spawner = new EnemySpawner();
        AudioEngine audio = new AudioEngine();
        Hud hud = new Hud();
        new MainMenu(assets, world, spawner, audio, hud).onNewGame();
        check(spawner.enemies(), audio.theme());

        Console.step("Next day: the game starts again and the player clicks 'Continue'");
        assets = new AssetLoader();
        world = new WorldGenerator();
        spawner = new EnemySpawner();
        audio = new AudioEngine();
        hud = new Hud();
        new ContinueButton(assets, world, spawner, hud).onClick();
        check(spawner.enemies(), audio.theme());

        Console.note("Refactor task: add a GameSession facade with startLevel(level); the "
                + "buttons call only that method and never see a subsystem.");
    }

    private static void check(int enemies, String theme) {
        if (enemies > 0) {
            Console.ok("the level has " + enemies + " enemies");
        } else {
            Console.fail("the level has no enemies -- they were spawned before the world existed");
        }
        if (theme != null) {
            Console.ok("the " + theme + " theme is playing");
        } else {
            Console.fail("the level is silent -- nobody started the music");
        }
    }
}
