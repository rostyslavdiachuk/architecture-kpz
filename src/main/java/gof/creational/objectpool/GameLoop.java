package gof.creational.objectpool;

/** Runs the game at 60 FPS and counts frames that blew the 16 ms budget. */
public class GameLoop {

    private static final int FRAME_BUDGET_MS = 16;
    private static final int WORK_PER_FRAME_MS = 10;
    private static final int SHOTS_PER_FRAME = 4;

    private final Turret turret;
    private int droppedFrames;

    public GameLoop(Turret turret) {
        this.turret = turret;
    }

    public void run(int frames) {
        for (int frame = 0; frame < frames; frame++) {
            int frameMs = WORK_PER_FRAME_MS;
            for (int shot = 0; shot < SHOTS_PER_FRAME; shot++) {
                frameMs += turret.fire();
            }
            turret.update();
            if (frameMs > FRAME_BUDGET_MS) {
                droppedFrames++;
            }
        }
    }

    public int droppedFrames() {
        return droppedFrames;
    }
}
