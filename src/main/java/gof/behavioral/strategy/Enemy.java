package gof.behavioral.strategy;

/** Holds a strategy and delegates to it. New behaviors don't touch this class. */
public class Enemy {

    private final String name;
    private AiStrategy ai;

    public Enemy(String name, AiStrategy ai) {
        this.name = name;
        this.ai = ai;
    }

    public String name() {
        return name;
    }

    public void setAi(AiStrategy ai) {
        this.ai = ai;
    }

    public String act(int distanceToHero) {
        return ai.act(distanceToHero);
    }
}
