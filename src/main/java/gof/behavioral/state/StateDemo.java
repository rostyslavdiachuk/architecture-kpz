package gof.behavioral.state;

import common.Console;

public class StateDemo {

    private record Step(Input input, String expected) {
    }

    public static void main(String[] args) {
        Console.header("GoF / State -- the hero's state is a pile of booleans");

        HeroController hero = new HeroController();

        Step[] script = {
                new Step(Input.JUMP, "jump"),
                new Step(Input.ATTACK, "dive attack"),
                new Step(Input.LAND, "land"),
                new Step(Input.DUCK, "duck"),
                new Step(Input.ATTACK, "low sweep"),
                new Step(Input.STAND_UP, "stand up"),
                new Step(Input.STUN, "stunned"),
                new Step(Input.ATTACK, "ignored"),
                new Step(Input.DUCK, "ignored"),
                new Step(Input.JUMP, "ignored"),
                new Step(Input.WAKE_UP, "wake up"),
                new Step(Input.ATTACK, "slash"),
        };

        for (Step step : script) {
            String actual = hero.handle(step.input());
            if (actual.equals(step.expected())) {
                Console.ok(step.input() + " -> " + actual);
            } else {
                Console.fail(step.input() + " -> " + actual + ", but it should be: " + step.expected());
            }
        }

        Console.note("Refactor task: one class per state (Standing, Jumping, Ducking, Stunned); "
                + "the hero delegates each input to its current state, and the state picks the next one.");
    }
}
