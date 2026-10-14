package grasp.polymorphism;

import common.Console;

public class PushChannel implements Channel {

    @Override
    public void deliver(String recipient, String text) {
        Console.step("PUSH -> device(" + recipient + ") : " + text);
    }

    @Override
    public String previewLabel(String recipient, String text) {
        return "Push to device " + recipient;
    }

    @Override
    public double costUah(String text) {
        return 0.01;
    }
}
